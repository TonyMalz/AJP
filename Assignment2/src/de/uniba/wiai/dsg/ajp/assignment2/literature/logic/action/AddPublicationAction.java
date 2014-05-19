package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;
import java.util.HashMap;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;

public class AddPublicationAction extends DatabaseAction {

    private final Author[] authors;

    private String pubId;
    private String pubTitle;
    private int pubYear;
    private PublicationType pubType;
    private final HashMap<String, Author> pubAuthors = new HashMap<>();

    public AddPublicationAction(DatabaseRequest request) {
	super(request);
	authors = database.getAuthors();
    }

    @Override
    public void show() {
	System.out.println("\n\t ADD PUBLICATION:");
    }

    @Override
    public void readInput() throws IOException {

	if (authors.length == 0) {
	    System.out
		    .println(">>> Warning: currently there aren't any authors.");
	    System.out.println("Please add an author first.");
	    console.askString("Press any key to continue...");
	    setNextRequest(Request.ADD_AUTHOR);
	    return;
	}

	pubTitle = console.askNonEmptyString("Please enter a title:");
	pubId = getPublicationId();
	pubYear = console.askIntegerInRange("Please enter year published:", 0,
		2014);
	pubType = getPublicationType();

	selectPublicationAuthors();

    }

    private void selectPublicationAuthors() throws IOException {
	while (true) {
	    System.out.println("Please select an author:");

	    int minOption = 1;
	    int i = 0;

	    // list all authors not added yet
	    Author[] authorsToSelectedFrom = new Author[authors.length];
	    for (Author author : authors) {
		if (pubAuthors.containsKey(author.getId())) {
		    continue;
		}
		authorsToSelectedFrom[i] = author;
		System.out.println("( " + (++i) + " )" + author);
	    }
	    int maxOption = i;

	    // no authors left to add
	    if (maxOption == 0) {
		return;
	    }

	    // get at least one author
	    if (!pubAuthors.isEmpty()) {
		System.out.println("--------------");
		System.out.println("( 0 ) continue");
		minOption = 0;
	    }

	    int selectedOption = selectOption(minOption, maxOption);
	    if (selectedOption == 0) {
		return;
	    } else {
		Author selectedAuthor = authorsToSelectedFrom[selectedOption - 1];
		pubAuthors.put(selectedAuthor.getId(), selectedAuthor);
	    }
	}

    }

    /**
     * Asks the user for a publication type. <br>
     * It can be one of the following: <br>
     * ARTICLE, TECHREP, BOOK, MASTERSTHESIS, PHDTHESIS, INPROCEEDINGS
     * 
     * @return the inserted publication type
     * @throws IOException
     */
    private PublicationType getPublicationType() throws IOException {
	System.out.println("Please select the type of the publication:");

	int i = 1;
	for (PublicationType publicationType : PublicationType.values()) {
	    System.out.println("( " + (i++) + " )" + publicationType);
	}
	final int type = selectOption(1, PublicationType.values().length);

	return PublicationType.values()[type - 1];
    }

    /**
     * asks the user for a new Id for an author. IF the id is already used in
     * the dataBase than he is asked for another one.
     * 
     * @return the ID for the new Author
     * @throws IOException
     */
    private String getPublicationId() throws IOException {
	while (true) {

	    final String id = console.askNonEmptyString("Please enter an ID:");

	    if (!ValidationHelper.isId(id)) {
		System.out.println("ERROR: The ID entered is not a valid ID");
	    } else if (isPublicationIDUsed(id)) {
		System.out
			.println("ERROR: The ID is already in use. Please chose another one.");
	    } else {
		return id;
	    }
	}
    }

    private boolean isPublicationIDUsed(String id) {
	for (final Publication publication : database.getPublications()) {
	    if (publication.getId().equals(id)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public void validateInput() throws IOException {
    }

    @Override
    public void process() throws IOException {

	try {
	    Author[] selectedAuthors = new Author[1];
	    selectedAuthors = pubAuthors.values().toArray(selectedAuthors);
	    database.addPublication(pubTitle, pubYear, pubType,
		    selectedAuthors, pubId);

	    System.out.println("\n>>> Successfully added Publication:");
	    System.out.println("\t Title:\t" + pubTitle);
	    System.out.println("\t Id:\t" + pubId);
	    System.out.println("\t Year:\t" + pubYear);
	    System.out.println("\t Type:\t" + pubType);

	} catch (LiteratureDatabaseException e) {
	    System.out
		    .println("\n>>> ERROR while adding publication to the database");
	    System.out.println(e);
	}

	setNextRequest(Request.SHOW_DATABASE_MENU);
    }
}
