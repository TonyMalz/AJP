package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;

public class CreateDatabaseAction extends DatabaseAction {
    /** name of the datbase. */
    private String newName;
    /** path to the database. */
    private Path databaseFile;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public CreateDatabaseAction(final DatabaseRequest request) {
	super(request);
    }

    @Override
    public void show() {
	System.out.println("\n\t CREATE DATABASE");
	System.out.println("Current databases:");
	for (final Path file : getXMLFilesInDirectory()) {
	    System.out.println(file.toString());
	}
	System.out.println("--------------");
	System.out.println("( 0 ) cancel");
    }

    @Override
    public void readInput() throws IOException {
	newName = console.askNonEmptyString("Please enter a new name:");
	if (newName.equals("0")) {
	    setNextRequest(Request.SHOW_MAIN_MENU);
	}
    }

    @Override
    public void validateInput() throws IOException {
	newName = getFileName();
	databaseFile = Paths.get(newName);

	if (Files.exists(databaseFile)) {
	    System.out.println("\n>>> ERROR: Database " + newName
		    + " already exists");
	    setNextRequest(Request.CREATE_DATABASE);
	}
    }

    @Override
    public void process() throws IOException {

	try {
	    getDatabase().saveXMLToFile(newName);
	    setDatabaseFileName(newName);

	    System.out.println("\n>>> Database " + newName + " (" + newName
		    + ") created successfully.");

	    setNextRequest(Request.SHOW_DATABASE_MENU);

	} catch (final LiteratureDatabaseException e) {
	    System.out.println("\n>>> ERROR: Database " + newName
		    + " could not be created!");
	    setNextRequest(Request.CREATE_DATABASE);
	}

    }

    /**
     * adds a .xml after the name when not yet there.
     * 
     * @return the name +".xml"
     */
    private String getFileName() {
	return newName.matches(".+\\.xml$") ? newName : newName + ".xml";
    }

}
