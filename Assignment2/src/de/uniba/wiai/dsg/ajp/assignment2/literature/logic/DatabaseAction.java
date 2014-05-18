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
    protected static ConsoleHelper console = ConsoleHelper.build();
    protected Request nextRequest = null;
    protected DatabaseService database;

    protected void setNextRequest(Request request) {
	nextRequest = request;
    }

    protected Request getNextRequest() {
	return nextRequest;
    }

    protected void setDatabase(DatabaseService database) {
	this.database = database;
    }

    protected final DatabaseRequest request;

    public DatabaseAction(DatabaseRequest request) {
	this.request = request;
	database = request.getDatabase();
    }

    public DatabaseService getDatabase() {
	return database;
    }

    public abstract void show();

    public abstract void readInput() throws IOException;

    public abstract void validateInput() throws IOException;

    public abstract void process() throws IOException;

    public Request processRequest() {
	try {
	    show();
	    readInput();
	    validateInput();
	    // only process action if no request has been set yet
	    if (nextRequest == null) {
		process();
	    }

	} catch (IOException e) {
	    System.out
		    .println("An internal error occured while reading from console, sorry!");
	    setNextRequest(Request.EXIT);
	}

	return getNextRequest();
    }

    protected int selectOption(int min, int max) throws IOException {

	return console.askIntegerInRange("Please choose an option:", min, max);

    }

    protected List<Path> getXMLFilesInDirectory() {
	return getXMLFilesInDirectory(1);
    }

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
			public FileVisitResult postVisitDirectory(Path dir,
				IOException exc) throws IOException {
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
				BasicFileAttributes attrs) throws IOException {
			    if (file.toString().endsWith(".xml")) {
				xmlFiles.add(file);
			    }
			    return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file,
				IOException exc) throws IOException {
			    return FileVisitResult.CONTINUE;
			}
		    });
	} catch (IOException e) {
	}

	return xmlFiles;
    }

    protected void dropCurrentDatabase() {
	this.request.getDatabase().clear();
	this.request.setDatabaseFileName(null);
    }

    protected String getDatabaseFileName() {
	return request.getDatabaseFileName();
    }

    protected void setDatabaseFileName(String fileName) {
	request.setDatabaseFileName(fileName);
    }

    protected void setDatabase(DatabaseService database, String fileName) {
	request.setDatabaseFileName(fileName);
	request.setDatabase(database);
    }

}
