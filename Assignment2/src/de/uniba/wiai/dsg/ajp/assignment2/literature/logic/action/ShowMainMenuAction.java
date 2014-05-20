package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;

public class ShowMainMenuAction extends DatabaseAction {

    /** the choice of the user. */
    private int option = -1;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */

    public ShowMainMenuAction(final DatabaseRequest request) {
	super(request);
	// always start with clean database
	request.getDatabase().clear();
	request.setDatabaseFileName(null);
    }

    @Override
    public void show() {
	System.out.println("\n\t MAIN MENU:");
	System.out.println("( 1 ) Validate and Load Literature Database");
	System.out.println("( 2 ) Create New Literature Database");
	System.out.println("( 0 ) Exit System");

    }

    @Override
    public void readInput() throws IOException {
	option = selectOption(0, 2);
    }

    @Override
    public Request getNextRequest() {
	switch (option) {
	case 0:
	    return Request.EXIT;
	case 1:
	    return Request.LOAD_DATABASE;
	case 2:
	    return Request.CREATE_DATABASE;
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
