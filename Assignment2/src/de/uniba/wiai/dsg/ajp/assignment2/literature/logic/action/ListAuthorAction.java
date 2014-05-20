package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;

public class ListAuthorAction extends DatabaseAction {
    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public ListAuthorAction(final DatabaseRequest request) {
	super(request);
    }

    @Override
    public void show() {
	System.out.println("\n\t LIST AUTHORS:");
	for (final Author author : database.getAuthors()) {
	    System.out.println(author);
	}
    }

    @Override
    public void readInput() throws IOException {
	console.askString("\nPlease press any key to continue...");
    }

    @Override
    public void validateInput() throws IOException {

    }

    @Override
    public void process() throws IOException {
	setNextRequest(Request.SHOW_DATABASE_MENU);
    }

}
