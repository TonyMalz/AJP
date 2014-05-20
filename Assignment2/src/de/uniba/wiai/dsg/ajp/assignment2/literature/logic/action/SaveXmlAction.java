package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;

public class SaveXmlAction extends DatabaseAction {
    private String fileName;
    private final List<Path> databaseList;

    public SaveXmlAction(DatabaseRequest request) {
	super(request);
	databaseList = getXMLFilesInDirectory();
    }

    @Override
    public void show() {
	System.out.println("\n\t SAVE DATABASE:");
	System.out.println("Current databases:");
	int i = 1;
	for (Path file : databaseList) {
	    System.out.println("(" + (i++) + ") " + file.toString());
	}
	System.out.println("--------------");
	System.out.println("( 0 ) cancel");
    }

    @Override
    public void readInput() throws IOException {
	fileName = console
		.askNonEmptyString("Please select a database or enter new location:"
			+ "\n(Warning: Existing database will be overwritten)");

    }

    @Override
    public void validateInput() throws IOException {
	if (fileName.equals("0")) {
	    setNextRequest(Request.SHOW_DATABASE_MENU);
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
		    setNextRequest(Request.SAVE_XML);
		    return;
		}
	    } catch (NumberFormatException e) {
		System.out.println("\n>>> Error parsing option: " + fileName);
		setNextRequest(Request.SAVE_XML);
		return;
	    }
	}

	fileName = formatFileName();
    }

    @Override
    public void process() throws IOException {

	try {
	    System.out.println("\n>>> Saving database...");
	    getDatabase().saveXMLToFile(fileName);
	    setDatabaseFileName(fileName);

	    System.out.println(">>> Database " + fileName
		    + " saved successfully.");

	    setNextRequest(Request.SHOW_DATABASE_MENU);

	} catch (LiteratureDatabaseException e) {
	    System.out.println("\n>>> ERROR: Database " + fileName
		    + " could not be saved!");
	    System.out.println("Reason:\n" + e.getMessage());
	    setNextRequest(Request.SAVE_XML);
	}

    }

    private String formatFileName() {
	return fileName.matches(".+\\.xml$") ? fileName : fileName + ".xml";
    }
}
