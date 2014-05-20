package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;

public class AddAuthorAction extends DatabaseAction {
    /** name of the author. */
    private String authorName;
    /** id of the author. */
    private String authorId;
    /** email of the author. */
    private String authorEmail;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public AddAuthorAction(final DatabaseRequest request) {
	super(request);
    }

    @Override
    public void show() {
	System.out.println("\n\t ADD AUTHOR");
    }

    @Override
    public void readInput() throws IOException {
	authorName = console.askNonEmptyString("Please enter a name:");
	authorId = getAuthorID();
	authorEmail = getEmail();
    }

    /**
     * asks the user for a new Id for an author. IF the id is already used in
     * the dataBase than he is asked for another one.
     * 
     * @return the ID for the new Author
     * @throws IOException
     */
    private String getAuthorID() throws IOException {
	while (true) {

	    final String id = console.askNonEmptyString("Please enter an ID:");

	    if (!ValidationHelper.isId(id)) {
		System.out.println("ERROR: The ID entered is not a valid ID");
	    } else if (isAuthorIDUsed(id)) {
		System.out
			.println("ERROR: The ID is already in use. Please chose another one.");
	    } else {
		return id;
	    }
	}
    }

    /**
     * Asks the user for a email address. if it is not valid he is asked again.
     * 
     * @return the inserted email
     * @throws IOException
     *             in case an error occurs while trying to read the console.
     */
    private String getEmail() throws IOException {
	while (true) {

	    final String email = console
		    .askNonEmptyString("Please enter a valid email:");

	    if (!ValidationHelper.isEmail(email)) {
		// input is not a id
		System.out
			.println("ERROR: The email entered is not a valid. Please enter another one.");
	    } else {
		return email;
	    }
	}
    }

    /**
     * looks if the ID is already used in the database
     * 
     * @param id
     *            to be checked
     * @return true when Id is already used, false otherwise
     */
    private boolean isAuthorIDUsed(final String id) {
	for (final Author author : database.getAuthors()) {
	    if (author.getId().equals(id)) {
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
	    database.addAuthor(authorName, authorEmail, authorId);
	    System.out.println("\n>>> Successfully added author:");
	    System.out.println("\t name:\t" + authorName);
	    System.out.println("\t id:\t" + authorId);
	    System.out.println("\t email:\t" + authorEmail);

	} catch (final LiteratureDatabaseException e) {
	    System.out
		    .println("\n>>> ERROR while adding author to the database");
	    System.out.println(e);
	}

	setNextRequest(Request.SHOW_DATABASE_MENU);
    }
}
