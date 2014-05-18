package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;

public class PrintXmlAction extends DatabaseAction {

    public PrintXmlAction(DatabaseRequest request) {
	super(request);
    }

    @Override
    public void show() {
	System.out.println("\nDatabase: " + getDatabaseFileName());// TODO DEBUG
	try {
	    database.printXMLToConsole();
	} catch (LiteratureDatabaseException e) {
	    System.out.println("An error occured while printing...");
	}
    }

    @Override
    public void readInput() throws IOException {
	console.askString("\nPress any key to continue...");
    }

    @Override
    public void validateInput() throws IOException {

    }

    @Override
    public void process() throws IOException {
	setNextRequest(Request.SHOW_DATABASE_MENU);
    }

}
