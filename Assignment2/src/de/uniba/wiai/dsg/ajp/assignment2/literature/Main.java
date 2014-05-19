package de.uniba.wiai.dsg.ajp.assignment2.literature;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.AddAuthorAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.AddPublicationAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.CreateDatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.LoadDatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.PrintXmlAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.RemoveAuthorAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.RemovePublicationAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ShowDatabaseMenuAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ShowMainMenuAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.DatabaseServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;

public class Main {

    private static DatabaseRequest request;
    private static boolean exit;
    private static DatabaseAction action;

    public static void main(String[] args) {
	init();
	while (!exit) {
	    route(request);
	}
    }

    private static void init() {
	exit = false;
	request = new DatabaseRequest(Request.SHOW_MAIN_MENU);
    }

    private static void route(DatabaseRequest request) {

	switch (request.getCurrentRequest()) {
	case EXIT:
	    exit = true;
	    System.out.println("Bye...");
	    break;

	case SHOW_MAIN_MENU:
	    action = new ShowMainMenuAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case CREATE_DATABASE:
	    action = new CreateDatabaseAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case LOAD_DATABASE:
	    action = new LoadDatabaseAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case SHOW_DATABASE_MENU:
	    action = new ShowDatabaseMenuAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case ADD_AUTHOR:
	    action = new AddAuthorAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case REMOVE_AUTHOR:
	    action = new RemoveAuthorAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case ADD_PUBLICATION:
	    action = new AddPublicationAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case REMOVE_PUBLICATION:
	    action = new RemovePublicationAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case PRINT_XML:
	    action = new PrintXmlAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	default:
	    System.out.println("ERROR: Unimplemented Action:"
		    + request.getCurrentRequest().toString());
	    exit = true;
	    break;
	}

    }

    public enum Request {
	SHOW_MAIN_MENU, SHOW_DATABASE_MENU, EXIT, LOAD_DATABASE, CREATE_DATABASE, ADD_AUTHOR, REMOVE_AUTHOR, ADD_PUBLICATION, REMOVE_PUBLICATION, LIST_PUBLICATIONS, LIST_AUTHORS, PRINT_XML, SAVE_XML

    }

    public static class DatabaseRequest {

	private Request request;
	private final MainService mainService = new MainServiceImpl();
	private DatabaseService dbs = new DatabaseServiceImpl();
	private String dbFileName;

	public DatabaseRequest(Request request) {
	    this.request = request;
	}

	public Request getCurrentRequest() {
	    return request;
	}

	public void setNextRequest(Request request) {
	    this.request = request;
	}

	public DatabaseService getDatabase() {
	    return dbs;
	}

	public MainService getMainService() {
	    return mainService;
	}

	public String getDatabaseFileName() {
	    return dbFileName;
	}

	public void setDatabaseFileName(String fileName) {
	    dbFileName = fileName;
	}

	public void setDatabase(DatabaseService database) {
	    dbs = database;
	}
    }

}
