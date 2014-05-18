package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;

public class LoadDatabaseAction extends DatabaseAction {
    private String fileName;
    private Path databaseFile;
    private final List<Path> databaseList;

    private final static MainService mainService = new MainServiceImpl();

    public LoadDatabaseAction(DatabaseRequest request) {
	super(request);
	databaseList = getXMLFilesInDirectory();
    }

    @Override
    public void show() {
	System.out.println("\n\t LOAD DATABASE:");
	System.out.println("( 0 ) cancel");
	System.out.println("--------------");
	int i = 1;
	for (Path file : databaseList) {
	    System.out.println("( " + (i++) + " ) " + file);
	}
    }

    @Override
    public void readInput() throws IOException {
	fileName = console
		.askNonEmptyString("Please enter a file name or number:");
    }

    @Override
    public void validateInput() throws IOException {

	if (fileName.equals("0")) {
	    setNextRequest(Request.SHOW_MAIN_MENU);
	    return;
	}

	// handle file selection
	if (fileName.matches("\\d+")) {
	    try {
		int selectedFile = Integer.valueOf(fileName);
		if (selectedFile > 0 && selectedFile <= databaseList.size()) {
		    fileName = databaseList.get(selectedFile - 1).toString();
		} else {
		    System.out.println("\n>>> Invalid option: " + fileName);
		    setNextRequest(Request.LOAD_DATABASE);
		    return;
		}
	    } catch (NumberFormatException e) {
		System.out.println("\n>>> Error parsing option: " + fileName);
		setNextRequest(Request.LOAD_DATABASE);
		return;
	    }
	}

	fileName = getFileName();
	// get relative file path
	databaseFile = Paths.get(fileName);

	if (!Files.exists(databaseFile)) {
	    System.out.println("\n>>> Error: Database with name " + fileName
		    + " does not exist!");
	    setNextRequest(Request.LOAD_DATABASE);
	    return;
	} else if (!Files.isWritable(databaseFile)) {
	    System.out.println("\n>>> Warning: Database with name " + fileName
		    + " is read-only");
	}

	try {
	    mainService.validate(fileName);
	} catch (LiteratureDatabaseException e) {
	    System.out.println(e.getMessage());
	    setNextRequest(Request.LOAD_DATABASE);
	}

    }

    @Override
    public void process() {
	try {
	    System.out.println("\n>>> Loading database " + fileName);
	    database = mainService.load(fileName);
	    setDatabase(database, fileName);
	    setNextRequest(Request.SHOW_DATABASE_MENU);

	} catch (LiteratureDatabaseException e) {
	    System.out.println(e);
	    setNextRequest(Request.SHOW_MAIN_MENU);
	}

    }

    private String getFileName() {
	return fileName.matches(".+\\.xml$") ? fileName : fileName + ".xml";
    }

}
