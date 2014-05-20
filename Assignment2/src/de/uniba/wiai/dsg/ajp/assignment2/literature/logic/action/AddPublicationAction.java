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
    /** authors of the database. */
    private final Author[] authors;
    /** ID of the publication. */
    private String pubId;
    /** title of the publication. */
    private String pubTitle;
    /** publication year of the publication. */
    private int pubYear;
    /** type of the publication. */
    private PublicationType pubType;
    /** authors of the publication. */
    private final HashMap<String, Author> pubAuthors = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public AddPublicationAction(final DatabaseRequest request) {
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

    /**
     * Asks the user which author has written the publication.
     * 
     * @throws IOException
     *             in case of error.
     */
    private void selectPublicationAuthors() throws IOException {
	while (true) {
	    System.out.println("Please select an author:");

	    int minOption = 1;
	    int i = 0;

	    // list all authors not added yet
	    final Author[] authorsToSelectedFrom = new Author[authors.length];
	    for (final Author author : authors) {
		if (pubAuthors.containsKey(author.getId())) {
		    continue;
		}
		authorsToSelectedFrom[i] = author;
		System.out.println("( " + ++i + " )" + author);
	    }
	    // TODO ( 0 ) new Author
	    final int maxOption = i;

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

	    final int selectedOption = selectOption(minOption, maxOption);
	    if (selectedOption == 0) {
		return;
	    } else {
		final Author selectedAuthor = authorsToSelectedFrom[selectedOption - 1];
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
	for (final PublicationType publicationType : PublicationType.values()) {
	    System.out.println("( " + i++ + " )" + publicationType);
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

    /**
     * Checks if the id is used as an ID for a publication.
     * 
     * @param id
     *            to be checked
     * @return true when NOT used, false otherwise
     */
    private boolean isPublicationIDUsed(final String id) {
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

	} catch (final LiteratureDatabaseException e) {
	    System.out
		    .println("\n>>> ERROR while adding publication to the database");
	    System.out.println(e);
	}

	setNextRequest(Request.SHOW_DATABASE_MENU);
    }
}
