package de.uniba.wiai.dsg.ajp.assignment2.literature.ui;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;

public class DatabaseConfigurationGUI {

    private final ConsoleHelper consoleHelper = ConsoleHelper.build();
    private DatabaseService dataBaseService;
    private final MainService mainService = new MainServiceImpl();

    public DatabaseConfigurationGUI() throws LiteratureDatabaseException {
	try {
	    boolean run = true;
	    while (run) {
		printMainMenu();
		final int choice = consoleHelper.askIntegerInRange("", 0, 2);
		switch (choice) {
		case 1:
		    subMenu();
		    break;
		case 2:
		    dataBaseService = mainService.create();
		    break;
		case 0:
		default:
		    run = false;

		}

	    }
	} catch (final IOException e) {
	    throw new LiteratureDatabaseException(e);
	}
    }

    /**
     * Prints the main menu to the console.
     */
    private static void printMainMenu() {
	System.out.println("\t MAIN MENU:");
	System.out.println("( 1 ) Validate and Load Literature Database");
	System.out.println("( 2 ) Create New Literature Database");
	System.out.println("( 0 ) Exit System");
    }

    private void subMenu() throws IOException, LiteratureDatabaseException {
	printSubMenu();
	final int choice = consoleHelper.askIntegerInRange("", 0, 8);
	switch (choice) {
	case 1:
	    addAuthor();
	    break;
	case 2:
	    removeAuthor();
	    break;
	case 3:
	    addPublication();
	    break;
	case 4:
	    removePublication();
	    break;
	case 5:
	    ListPublication();
	    break;
	case 6:
	    ListAuthors();
	    break;
	case 7:
	    printXMLConsole();
	    break;
	case 8:
	    printXMLFile();
	    break;

	case 0:
	default:
	    // do nothing and go back to the main menu
	    break;
	}
    }

    /**
     * Prits the sub menu to the console.
     */
    private static void printSubMenu() {
	System.out.println("( 1 ) Add Author");
	System.out.println("( 2 ) Remove Author");
	System.out.println("( 3 ) Add Publication");
	System.out.println("( 4 ) Remove Publication");
	System.out.println("( 5 ) List Publications");
	System.out.println("( 6 ) List Authors");
	System.out.println("( 7 ) Print XML on Console");
	System.out.println("( 8 ) Save XML to File");
	System.out.println("( 0 ) Back to main menu / close without saving");
    }

    private void printXMLFile() throws IOException, LiteratureDatabaseException {

	final String path = consoleHelper
		.askNonEmptyString("input a a valid path for the xml file.");
	dataBaseService.saveXMLToFile(path);
    }

    private void printXMLConsole() throws LiteratureDatabaseException {
	dataBaseService.printXMLToConsole();

    }

    private void ListAuthors() {
	for (final Author author : dataBaseService.getAuthors()) {
	    System.out.println(author.toString());
	}
    }

    private void ListPublication() {
	for (final Publication publication : dataBaseService.getPublications()) {
	    System.out.println(publication.toString());
	}

    }

    private void removePublication() {
	// TODO Auto-generated method stub

    }

    private void addPublication() throws LiteratureDatabaseException {
	final Publication pubToAdd = getNewPublication();
	dataBaseService.addPublication(pubToAdd.getTitle(), pubToAdd
		.getYearPublished(), pubToAdd.getType(), pubToAdd.getAuthors()
		.toArray(new Author[0]), pubToAdd.getId());

    }

    private void removeAuthor() {
	// TODO Auto-generated method stub

    }

    private void addAuthor() throws LiteratureDatabaseException {
	final Author authortoAdd = getNewAuthor();
	dataBaseService.addAuthor(authortoAdd.getName(),
		authortoAdd.getEmail(), authortoAdd.getId());

    }

    private Publication getNewPublication() {
	// TODO Auto-generated method stub
	return null;
    }

    private Author getNewAuthor() {
	// TODO Auto-generated method stub
	return null;
    }

    public static void main(final String[] args) {

    }

}
