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

public abstract class DatabaseAction {
    // TODO comment
    protected static ConsoleHelper console = ConsoleHelper.build();
    protected Request nextRequest = null;
    protected DatabaseService database;
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

    // TODO comment
    public abstract void show();

    public abstract void readInput() throws IOException;

    public abstract void validateInput() throws IOException;

    public abstract void process() throws IOException;

    /**
     * Tries to process the request. <br>
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
