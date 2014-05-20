package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;

public class RemoveAuthorAction extends DatabaseAction {
    /** author in the datbase. */
    private final Author[] authors;
    /** the choice of the user. */
    private int option = -1;
    /** Author to be removed. */
    private Author authorToRemove;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public RemoveAuthorAction(final DatabaseRequest request) {
	super(request);
	authors = database.getAuthors();
    }

    @Override
    public void show() {
	// TODO show removed publications
	System.out.println("\n\t REMOVE AUTHOR");
	int i = 1;
	for (final Author author : authors) {
	    System.out.println("( " + i++ + " )" + author);
	}
	System.out.println("--------------");
	System.out.println("( 0 ) cancel");
    }

    @Override
    public void readInput() throws IOException {
	option = console.askIntegerInRange(
		"Please select author to be removed:", 0, authors.length);
    }

    @Override
    public void validateInput() throws IOException {
	if (option == 0) {
	    setNextRequest(Request.SHOW_DATABASE_MENU);
	} else if (option > authors.length) {
	    System.out.println("\n>>> ERROR: invalid option selected!");
	    setNextRequest(Request.REMOVE_AUTHOR);
	} else {
	    authorToRemove = authors[option - 1];
	}
    }

    @Override
    public void process() throws IOException {

	try {
	    database.removeAuthorByID(authorToRemove.getId());
	    System.out.println("\n>>> Successfully removed author:");
	    System.out.println("\t name:\t" + authorToRemove.getName());
	    System.out.println("\t id:\t" + authorToRemove.getId());
	    System.out.println("\t email:\t" + authorToRemove.getEmail());

	} catch (final LiteratureDatabaseException e) {
	    System.out
		    .println("\n>>> ERROR while removing author from database");
	    System.out.println(e);
	}

	setNextRequest(Request.SHOW_DATABASE_MENU);
    }
}
