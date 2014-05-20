package de.uniba.wiai.dsg.ajp.assignment2.literature.logic;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.ui.ConsoleHelper;

/**
 * Abstract class for any database action <br>
 * 
 * Mainly used as a template for how to handle and process the database request
 * and subsequently trigger a new request.
 * 
 */
public abstract class DatabaseAction {
    /* helper object to read from console */
    protected static ConsoleHelper console = ConsoleHelper.build();
    /* the request that should be processed after this action finished */
    protected Request nextRequest = null;
    /* the current database */
    protected DatabaseService database;
    /* the global request object */
    protected final DatabaseRequest request;

    /**
     * Constructor.
     * 
     * @param request
     *            to be handled.
     */
    public DatabaseAction(final DatabaseRequest request) {
	this.request = request;
	database = request.getDatabase();
    }

    /**
     * sets the next request.
     * 
     * @param request
     *            to be set.
     */
    protected void setNextRequest(final Request request) {
	nextRequest = request;
    }

    /**
     * Gets the next request
     * 
     * @return the next request.
     */
    protected Request getNextRequest() {
	return nextRequest;
    }

    /**
     * Sets the database for the request.
     * 
     * @param database
     *            to be set
     */
    protected void setDatabase(final DatabaseService database) {
	this.database = database;
    }

    public DatabaseService getDatabase() {
	return database;
    }

    /**
     * Show content specific to this action
     */
    public abstract void show();

    /**
     * Ask user for input or any interactivity <br>
     * Called right after {@link #show()}
     * 
     * @throws IOException
     *             in case an error occurs while reading from console
     */
    public abstract void readInput() throws IOException;

    /**
     * Validate user input that was given in {@link #readInput()}<br>
     * Called right after {@link #readInput()}
     * 
     * @throws IOException
     *             in case an error occurs while reading from console
     */
    public abstract void validateInput() throws IOException;

    /**
     * Main processing method <br>
     * Should be used to carry out any specific logic/operations inherent to
     * this action <br>
     * Called right after {@link #validateInput()}
     * 
     * @throws IOException
     *             in case an error occurs while reading from console
     */
    public abstract void process() throws IOException;

    /**
     * Tries to process the current database request. <br>
     * (1) {@link #show()} <br>
     * (2) {@link #readInput()} <br>
     * (3) {@link #validateInput()} <br>
     * 
     * @return the next request
     */
    public Request processRequest() {
	try {
	    show();
	    readInput();
	    validateInput();
	    // only process action if no request has been set yet
	    if (nextRequest == null) {
		process();
	    }

	} catch (final IOException e) {
	    System.out
		    .println("An internal error occured while reading from console, sorry!");
	    setNextRequest(Request.EXIT);
	}

	return getNextRequest();
    }

    /**
     * Asks the user for a number in the range from min to max
     * 
     * @param min
     *            number to be selected
     * @param max
     *            number to be selected
     * @return the choice of the user
     * @throws IOException
     *             in case of error
     */
    protected int selectOption(final int min, final int max) throws IOException {

	return console.askIntegerInRange("Please choose an option:", min, max);

    }

    /**
     * Searches for .xml files in the directory on the first level.
     * 
     * @return file list
     */
    protected List<Path> getXMLFilesInDirectory() {
	return getXMLFilesInDirectory(1);
    }

    /**
     * searches for .xml files in the directory up to a given depth
     * 
     * @param maxDepth
     *            to be searched
     * @return the list of .xml files
     */
    protected List<Path> getXMLFilesInDirectory(int maxDepth) {
	if (maxDepth < 1) {
	    maxDepth = 1;
	}
	final List<Path> xmlFiles = new ArrayList<>();

	try {
	    Files.walkFileTree(Paths.get(""),
		    EnumSet.of(FileVisitOption.FOLLOW_LINKS), maxDepth,
		    new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(
				final Path dir, final IOException exc)
				throws IOException {
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(
				final Path dir, final BasicFileAttributes attrs)
				throws IOException {
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(final Path file,
				final BasicFileAttributes attrs)
				throws IOException {
			    if (file.toString().endsWith(".xml")) {
				xmlFiles.add(file);
			    }
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(final Path file,
				final IOException exc) throws IOException {
			    return FileVisitResult.CONTINUE;
			}
		    });
	} catch (final IOException e) {
	}

	return xmlFiles;
    }

    /**
     * Clears the database.
     */
    protected void dropCurrentDatabase() {
	request.getDatabase().clear();
	request.setDatabaseFileName(null);
    }

    /**
     * Get the name of the database.
     * 
     * @return the datbase name.
     */
    protected String getDatabaseFileName() {
	return request.getDatabaseFileName();
    }

    /**
     * Set the name of the database
     * 
     * @param fileName
     *            to set.
     */
    protected void setDatabaseFileName(final String fileName) {
	request.setDatabaseFileName(fileName);
    }

    /**
     * Sets the database and the filename
     * 
     * @param database
     *            to be set.
     * @param fileName
     *            to be set.
     */
    protected void setDatabase(final DatabaseService database,
	    final String fileName) {
	request.setDatabaseFileName(fileName);
	request.setDatabase(database);
    }

}
