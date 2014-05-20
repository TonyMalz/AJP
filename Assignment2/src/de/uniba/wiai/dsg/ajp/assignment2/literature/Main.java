package de.uniba.wiai.dsg.ajp.assignment2.literature;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.AddAuthorAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.AddPublicationAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.CreateDatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ListAuthorAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ListPublicationAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.LoadDatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.PrintXmlAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.RemoveAuthorAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.RemovePublicationAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.SaveXmlAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ShowDatabaseMenuAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action.ShowMainMenuAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.DatabaseServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;

public class Main {

    private static DatabaseRequest request;
    private static boolean exit;
    private static DatabaseAction action;

    public static void main(final String[] args) {
	init();
	while (!exit) {
	    route(request);
	}
    }

    private static void init() {
	exit = false;
	request = new DatabaseRequest(Request.SHOW_MAIN_MENU);
    }

    private static void route(final DatabaseRequest request) {

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

	case LIST_AUTHORS:
	    action = new ListAuthorAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case LIST_PUBLICATIONS:
	    action = new ListPublicationAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case PRINT_XML:
	    action = new PrintXmlAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	case SAVE_XML:
	    action = new SaveXmlAction(request);
	    request.setNextRequest(action.processRequest());
	    break;

	default:
	    System.out.println("ERROR: Unimplemented Action:"
		    + request.getCurrentRequest().toString());
	    exit = true;
	    break;
	}

    }

    /**
     * Contains all valid requests which are handled by corresponding database
     * action classes
     * 
     */
    public enum Request {
	SHOW_MAIN_MENU, SHOW_DATABASE_MENU, EXIT, LOAD_DATABASE, CREATE_DATABASE, ADD_AUTHOR, REMOVE_AUTHOR, ADD_PUBLICATION, REMOVE_PUBLICATION, LIST_PUBLICATIONS, LIST_AUTHORS, PRINT_XML, SAVE_XML

    }

    /**
     * Global Request Class <br>
     * <br>
     * Holds all mandatory database configuration objects: <br>
     * <ul>
     * <li>Current request object (request)</li>
     * <li>Current main service implementation (mainService)</li>
     * <li>Current database service implementation (dbs)</li>
     * <li>File name of current database (dbFileName)</li>
     * </ul>
     * 
     * This object is shared between all DatabaseAction implementations and is
     * mainly used as a container to query the current state of the application
     * including the current request
     * 
     */
    public static class DatabaseRequest {

	/* the current request object to be handled by an action */
	private Request request;
	/* the MainService interface implementation */
	private final MainService mainService = new MainServiceImpl();
	/*
	 * the database service implementation which provides access to current
	 * database
	 */
	private DatabaseService dbs = new DatabaseServiceImpl();
	/* the file name of the current database */
	private String dbFileName;

	/**
	 * @param request
	 *            the initial request
	 */
	public DatabaseRequest(final Request request) {
	    this.request = request;
	}

	/**
	 * @return the current request
	 */
	public Request getCurrentRequest() {
	    return request;
	}

	/**
	 * @param request
	 *            the request which should be handled next
	 */
	public void setNextRequest(final Request request) {
	    this.request = request;
	}

	/**
	 * @return current database service object
	 */
	public DatabaseService getDatabase() {
	    return dbs;
	}

	/**
	 * @return main service object
	 */
	public MainService getMainService() {
	    return mainService;
	}

	/**
	 * @return current database file name
	 */
	public String getDatabaseFileName() {
	    return dbFileName;
	}

	/**
	 * @param fileName
	 *            the file name / path which to save to
	 */
	public void setDatabaseFileName(final String fileName) {
	    dbFileName = fileName;
	}

	/**
	 * @param database
	 *            current database which should be globally used
	 */
	public void setDatabase(final DatabaseService database) {
	    dbs = database;
	}
    }

}
