package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.action;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.DatabaseRequest;
import de.uniba.wiai.dsg.ajp.assignment2.literature.Main.Request;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseAction;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;

public class RemovePublicationAction extends DatabaseAction {
    /** Publications of the database. */
    private final Publication[] publications;
    /** the choice of the user. */
    private int option = -1;
    /** Publication to be removed. */
    private Publication publicationToRemove;

    /**
     * Constructor.
     * 
     * @param request
     *            to be processed.
     */
    public RemovePublicationAction(final DatabaseRequest request) {
	super(request);
	publications = database.getPublications();
    }

    @Override
    public void show() {
	System.out.println("\n\t REMOVE PUBLICATION:");
	int i = 1;
	for (final Publication publication : publications) {
	    System.out.println("( " + i++ + " ) " + publication);
	}
	System.out.println("--------------");
	System.out.println("( 0 ) cancel");
    }

    @Override
    public void readInput() throws IOException {
	option = console.askIntegerInRange(
		"Please select publication to be removed:", 0,
		publications.length);
    }

    @Override
    public void validateInput() throws IOException {
	if (option == 0) {
	    setNextRequest(Request.SHOW_DATABASE_MENU);
	} else if (option > publications.length) {
	    System.out.println("\n>>> ERROR: invalid option selected!");
	    setNextRequest(Request.REMOVE_PUBLICATION);
	} else {
	    publicationToRemove = publications[option - 1];
	}
    }

    @Override
    public void process() throws IOException {

	try {
	    database.removePublicationByID(publicationToRemove.getId());
	    System.out.println("\n>>> Successfully removed publication:");
	    System.out.println("\t Title:\t" + publicationToRemove.getTitle());
	    System.out.println("\t ID:\t" + publicationToRemove.getId());
	    System.out.println("\t Type:\t" + publicationToRemove.getType());
	    System.out.println("\t Year:\t"
		    + publicationToRemove.getYearPublished());

	} catch (final LiteratureDatabaseException e) {
	    System.out
		    .println("\n>>> ERROR while removing publication from database");
	    System.out.println(e);
	}

	setNextRequest(Request.SHOW_DATABASE_MENU);
    }
}
