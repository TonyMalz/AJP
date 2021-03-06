package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;

public class ShowDatabaseMenuAction extends DatabaseAction {
    /** the choice of the user. */
    private int option = -1;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public ShowDatabaseMenuAction(final DatabaseRequest request) {
	super(request);
    }

    @Override
    public void show() {
	System.out.println("\n\t DATABASE " + getDatabaseFileName());
	System.out.println("( 1 ) Add Author");
	System.out.println("( 2 ) Remove Author");
	System.out.println("( 3 ) Add Publication");
	System.out.println("( 4 ) Remove Publication");
	System.out.println("( 5 ) List Publications");
	System.out.println("( 6 ) List Authors");
	System.out.println("( 7 ) Print XML on Console");
	System.out.println("( 8 ) Save XML to File");
	System.out.println("( 0 ) Back to main menu / close without saving");

    }

    @Override
    public void readInput() throws IOException {
	option = selectOption(0, 8);
    }

    @Override
    public Request getNextRequest() {
	switch (option) {
	case 1:
	    return Request.ADD_AUTHOR;
	case 2:
	    return Request.REMOVE_AUTHOR;
	case 3:
	    return Request.ADD_PUBLICATION;
	case 4:
	    return Request.REMOVE_PUBLICATION;
	case 5:
	    return Request.LIST_PUBLICATIONS;
	case 6:
	    return Request.LIST_AUTHORS;
	case 7:
	    return Request.PRINT_XML;
	case 8:
	    return Request.SAVE_XML;
	case 0:
	    return Request.SHOW_MAIN_MENU;
	default:
	    return Request.SHOW_MAIN_MENU;
	}

    }

    @Override
    public void validateInput() throws IOException {

    }

    @Override
    public void process() throws IOException {
    }

}
