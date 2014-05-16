package de.uniba.wiai.dsg.ajp.assignment2.literature.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;

public class DatabaseConfigurationGUI {
	/**
	 * Helper to ask the user for inputs.
	 */
	private final ConsoleHelper consoleHelper = ConsoleHelper.build();

	/**
	 * Helper to load and create a new DataBase.
	 */
	private final MainService mainService = new MainServiceImpl();

	/**
	 * the database to be modified. By default, an empty dataBase is created.
	 */
	private DatabaseService dataBaseService = mainService.create();

	/**
	 * Constructor. <br>
	 * the main menu is displayed and the user is asked for a action.
	 */
	public DatabaseConfigurationGUI() {
		// TODO Feedback auch bei erfolgreichen Aktionen
		boolean run = true;
		while (run) {
			try {

				printMainMenu();
				final int choice = consoleHelper.askIntegerInRange("", 0, 2);
				switch (choice) {
				case 1:
					loadDatabase();
					subMenu();
					break;
				case 2:
					createDatabase();
					subMenu();
					break;
				case 0:
				default:
					run = false;

				}
				// TODO catch in main menu or subenu???
			} catch (final IOException | LiteratureDatabaseException e) {
				System.out.println(e.getMessage()
						+ " Try again from the main menu.");

			}
		}
	}

	/**
	 * Prints the main menu to the console.
	 */
	private static void printMainMenu() {
		System.out.println("\n\t MAIN MENU:");
		System.out.println("( 1 ) Validate and Load Literature Database");
		System.out.println("( 2 ) Create New Literature Database");
		System.out.println("( 0 ) Exit System");
	}

	/**
	 * Prints the submenu. and asks the author for a input.
	 * 
	 * 
	 * @throws LiteratureDatabaseException
	 */
	private void subMenu() throws LiteratureDatabaseException {
		boolean runSubMenu = true;
		while (runSubMenu) {
			printSubMenu();
			try {
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
					saveXMLFile();
					break;

				case 0:
				default:
					runSubMenu = false;
					break;
				}
			} catch (final IOException e) {
				System.out
						.println("An error occured while trying to read the console. Try again from the main menu.");
				runSubMenu = false;
			}

		}
	}

	/**
	 * Prints the dataBase to a file. The user is asked for the path to be
	 * stored to.
	 * 
	 * @throws LiteratureDatabaseException
	 *             when the input/output stream to the console is closed <br>
	 *             when the output stream to the file is closed / flawed. <br>
	 *             when an error occurs while trying to marshal the database
	 */
	private void saveXMLFile() throws LiteratureDatabaseException {
		if (dataBaseService.getSavePath() == null) {
			setSavePath();
		} else {
			System.out.println("(1) Save to current file.");
			System.out.println("(0) Save to new Location.");
			try {
				final int choice = consoleHelper.askIntegerInRange("", 0, 1);
				if (choice == 0) {
					setSavePath();
				} else {
					System.out.println("Using the current location.");
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}
		dataBaseService.saveXMLToFile();
	}

	/**
	 * sets the save path of the mainService.
	 * 
	 * @return the path as a String
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String setSavePath() throws LiteratureDatabaseException {
		try {
			final String path = consoleHelper
					.askNonEmptyString("Enter a path where to save to:");
			dataBaseService.setSavePath(path);
			return path;
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}
	}

	/**
	 * Sets the load path of the mainService.
	 * 
	 * @return the path as a String
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String setLoadPath() throws LiteratureDatabaseException {
		String path;
		try {
			path = consoleHelper
					.askNonEmptyString("Enter a path where to load from:");

			dataBaseService.setSavePath(path);
			return path;
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}
	}

	/**
	 * Prints the dataBase to the console.
	 * 
	 * @throws LiteratureDatabaseException
	 *             when the marshaling fails.
	 */
	private void printXMLConsole() throws LiteratureDatabaseException {
		dataBaseService.printXMLToConsole();

	}

	/**
	 * Lists all Authors that are in the database.
	 */
	private void ListAuthors() {
		for (final Author author : dataBaseService.getAuthors()) {
			System.out.println(author.toString());
		}
	}

	/**
	 * Lists all publications that are in the database.
	 */
	private void ListPublication() {
		for (final Publication publication : dataBaseService.getPublications()) {
			System.out.println(publication.toString());
		}

	}

	/**
	 * Prints the sub menu to the console.
	 */
	private static void printSubMenu() {
		System.out.println("\n( 1 ) Add Author");
		System.out.println("( 2 ) Remove Author");
		System.out.println("( 3 ) Add Publication");
		System.out.println("( 4 ) Remove Publication");
		System.out.println("( 5 ) List Publications");
		System.out.println("( 6 ) List Authors");
		System.out.println("( 7 ) Print XML on Console");
		System.out.println("( 8 ) Save XML to File");
		System.out.println("( 0 ) Back to main menu / close without saving");

	}

	/**
	 * Removes a Publication from the DataBase. The user is asked for the id of
	 * the publication to be removed.
	 * 
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.<br>
	 *             in case an error occurs while trying to remove the
	 *             publication
	 */
	private void removePublication() throws LiteratureDatabaseException {
		try {
			final String id = consoleHelper
					.askNonEmptyString("Enter the ID of the Publication to be removed.");
			dataBaseService.removePublicationByID(id);
			System.out.println("Removed the publication with the ID " + id
					+ " from the database.");
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occure while trying to read the console.", e);
		}

	}

	/**
	 * Adds a publication to the database. The user is asked one after another
	 * for the title, the published year, the type and the authors.
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.<br>
	 *             in case an error occurs while trying to add the publication
	 *             to the database
	 */
	private void addPublication() throws LiteratureDatabaseException {
		final Publication pubToAdd = getNewPublication();
		dataBaseService.addPublication(pubToAdd.getTitle(), pubToAdd
				.getYearPublished(), pubToAdd.getType(), pubToAdd.getAuthors()
				.toArray(new Author[0]), pubToAdd.getId());
		System.out.println("Added the " + pubToAdd.getType() + " "
				+ pubToAdd.getTitle() + " published in "
				+ pubToAdd.getYearPublished() + " from the authors "
				+ pubToAdd.getAuthors().toString() + "to the database.");

	}

	/**
	 * Removes a author from the database. The user is asked for the id of the
	 * author to be removed.
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.<br>
	 *             in case an error occurs while trying to remove the author
	 *             from the database
	 */
	private void removeAuthor() throws LiteratureDatabaseException {
		try {
			final String id = consoleHelper

			.askNonEmptyString("Enter the ID of the Author to be removed.");
			dataBaseService.removeAuthorByID(id);
			System.out.println("removed the author with the ID " + id
					+ " from the database.");
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}
	}

	/**
	 * Adds a author to the database. The user is asked one after another for a
	 * name a email and an ID.
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.<br>
	 *             in case an error occurs while trying to add the publication
	 *             to the database
	 */
	private void addAuthor() throws LiteratureDatabaseException {
		final Author authortoAdd = getNewAuthor();
		dataBaseService.addAuthor(authortoAdd.getName(),
				authortoAdd.getEmail(), authortoAdd.getId());
		// TODO publications????
		System.out.println("Added the author " + authortoAdd.getName()
				+ " with the ID " + authortoAdd.getId() + " and the email "
				+ authortoAdd.getEmail() + " to the database.");

	}

	/**
	 * Asks the user for a new Publication.<br>
	 * (1) the title <br>
	 * (2) the year it was published <br>
	 * (3) the type<br>
	 * (4) the authors <br>
	 * Note that the user can chose whether he wants to add a new author or use
	 * an existing one.
	 * 
	 * @return the new publication
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private Publication getNewPublication() throws LiteratureDatabaseException {
		try {
			final String title = consoleHelper
					.askNonEmptyString("Enter the title of the Publication:");
			final String id = getPublicationIDUnused();
			final int year = consoleHelper.askIntegerInRange(
					"Enter published year:", 0, 2014);
			final PublicationType type = getPublicationType();
			final List<Author> authors = getAuthors();

			final Publication publication = new Publication();
			publication.setTitle(title);
			publication.setId(id);
			publication.setYearPublished(year);
			publication.setType(type);
			publication.setAuthors(authors);

			return publication;
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}
	}

	/**
	 * Asks the user for a list of authors. He can: <br>
	 * (1) use an exiting one. Than he is asked for the id of that author. <br>
	 * (2) create a new Author. The user is than asked for the n name his email
	 * and a valid id. <br>
	 * (3) not add a new one
	 * 
	 * @return the list of authors
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private List<Author> getAuthors() throws LiteratureDatabaseException {
		System.out.println("(1) add a exiting author by ID");
		System.out.println("(2) Add a new author");
		final List<Author> authors = new ArrayList<Author>();
		int choice;
		Author authorToAdd;
		try {
			choice = consoleHelper.askIntegerInRange("", 1, 2);
			authorToAdd = null;
			if (choice == 1) {
				final String idTOAdd = getAuthorIDUsed();
				for (final Author author : dataBaseService.getAuthors()) {
					if (author.getId().equals(idTOAdd)) {
						authorToAdd = author;
						break;
					}
				}

			} else {
				authorToAdd = getNewAuthor();

			}
			authors.add(authorToAdd);
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}

		while (true) {
			System.out.println("(1) add a exiting author by ID");
			System.out.println("(2) Add a new author");
			System.out.println("(0) No more authors");

			try {
				choice = consoleHelper.askIntegerInRange("", 0, 2);
				authorToAdd = null;
				if (choice == 1) {
					final String idTOAdd = getAuthorIDUsed();
					for (final Author author : dataBaseService.getAuthors()) {
						if (author.getId().equals(idTOAdd)) {
							authorToAdd = author;
							break;
						}
					}

				} else if (choice == 2) {
					authorToAdd = getNewAuthor();
				} else {
					break;
				}
				authors.add(authorToAdd);
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}

		return authors;
	}

	/**
	 * Asks the user for a publication type. <br>
	 * It can be one of the following: <br>
	 * ARTICLE, TECHREP, BOOK, MASTERSTHESIS, PHDTHESIS, INPROCEEDINGS
	 * 
	 * @return the inserted publication type
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private PublicationType getPublicationType()
			throws LiteratureDatabaseException {
		while (true) {
			try {
				final String type = consoleHelper
						.askNonEmptyString("Enter the type of the publication "
								+ "(ARTICLE, TECHREP, BOOK, MASTERSTHESIS, PHDTHESIS, INPROCEEDINGS)");
				switch (type) {
				case "ARTICLE":
					return PublicationType.ARTICLE;
				case "TECHREP":
					return PublicationType.TECHREP;
				case "BOOK":
					return PublicationType.BOOK;
				case "MASTERSTHESIS":
					return PublicationType.MASTERSTHESIS;
				case "PHDTHESIS":
					return PublicationType.PHDTHESIS;
				case "INPROCEEDINGS":
					return PublicationType.INPROCEEDINGS;
				default:
					System.out
							.println("Invalid Publication type. Enter a new one.");
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}

	}

	/**
	 * Asks the user for a new Author. <br>
	 * (1) the name <br>
	 * (2) the email. must ba valid email <br>
	 * (3) ID. this must be a String that is not yet sed for another Author
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private Author getNewAuthor() throws LiteratureDatabaseException {
		try {
			final String name = consoleHelper
					.askNonEmptyString("Enter the name of the Author:");
			final String id = getAuthorIDUnused();
			final String email = getEmail();

			final Author author = new Author();
			author.setName(name);
			author.setId(id);
			author.setEmail(email);
			return author;
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An error occured while trying to read the console.", e);
		}
	}

	/**
	 * Asks the user for a email address. if it is not valid he is asked again.
	 * 
	 * @return the inserted email
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String getEmail() throws LiteratureDatabaseException {
		while (true) {
			try {
				final String email = consoleHelper
						.askNonEmptyString("Enter a valid email:");
				if (!ValidationHelper.isEmail(email)) {
					// input is not a id
					System.out
							.println("The email entered is not a valid email please enter another one.");
				} else {
					return email;
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}
	}

	/**
	 * asks the user for a new Id for an author. IF the id is already used in
	 * the dataBase than he is asked for another one.
	 * 
	 * @return the ID for the new Author
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String getAuthorIDUnused() throws LiteratureDatabaseException {
		while (true) {
			try {
				final String id = consoleHelper
						.askNonEmptyString("Enter a ID that is not yet used:");
				if (!ValidationHelper.isId(id)) {
					// input is not a id
					System.out
							.println("The id entered is not a valid ID please enter another one.");
				} else if (isAuthorIDUsed(id, dataBaseService.getAuthors())) {
					// id is not already in use
					return id;
				} else {
					// id is already in use
					System.out
							.println("The ID is already used. Please chose another one.");
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}
	}

	/**
	 * asks the user for a new Id for an author. IF the id is not already used
	 * in the dataBase than he is asked for another one.
	 * 
	 * @return the ID of the author
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String getAuthorIDUsed() throws LiteratureDatabaseException {
		while (true) {
			try {
				final String id = consoleHelper
						.askNonEmptyString("Enter a author ID that is already in use:");
				if (!ValidationHelper.isId(id)) {
					// input is not a id
					System.out
							.println("The id entered is not a valid ID please enter another one.");
				} else if (!isAuthorIDUsed(id, dataBaseService.getAuthors())) {
					// id is not already in use
					return id;
				} else {
					// id is already in use
					System.out
							.println("The ID is not already used. Please chose another one.");
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}
	}

	/**
	 * looks if the ID is already used in the database
	 * 
	 * @param id
	 *            to be checked
	 * @param authors
	 *            the author that are checked for
	 * @return true when Id is already used, false otherwise
	 */
	private boolean isAuthorIDUsed(final String id, final Author[] authors) {
		for (final Author author : authors) {
			if (author.getId().equals(id)) {
				return false;
			}

		}
		return true;
	}

	/**
	 * asks the user for a new Id for an author. IF the id is already used in
	 * the dataBase than he is asked for another one.
	 * 
	 * @return the ID for the new Author
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to read the console.
	 */
	private String getPublicationIDUnused() throws LiteratureDatabaseException {
		while (true) {
			try {
				final String id = consoleHelper
						.askNonEmptyString("Enter a publication ID that is not yet used:");
				if (!ValidationHelper.isId(id)) {
					// input is not a id
					System.out
							.println("The id entered is not a valid ID please enter another one.");
				} else if (isPublicationIDUsed(id,
						dataBaseService.getPublications())) {
					// id is not already in use
					return id;
				} else {
					// id is already in use
					System.out
							.println("The ID is already used. Please chose another one.");
				}
			} catch (final IOException e) {
				throw new LiteratureDatabaseException(
						"An error occured while trying to read the console.", e);
			}
		}
	}

	private boolean isPublicationIDUsed(String id, Publication[] publications) {
		for (final Publication publication : publications) {
			if (publication.getId().equals(id)) {
				return false;
			}

		}
		return true;
	}

	/**
	 * loads and validates database by asking for path setSavePath to remember
	 * where to save this database
	 * 
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to set the path in case
	 *             an error occurs while trying to load/validate the database
	 */
	private void loadDatabase() throws LiteratureDatabaseException {
		final String path = setLoadPath();
		mainService.validate(path);
		dataBaseService = mainService.load(path);
		dataBaseService.setSavePath(path);

	}

	/**
	 * creates database by asking for save path setSavePath to remember where to
	 * save this database
	 * 
	 * @throws LiteratureDatabaseException
	 *             when an error occurs while trying to load the database
	 */
	private void createDatabase() throws LiteratureDatabaseException {
		dataBaseService = mainService.create();
	}

	/**
	 * Prints the given authors to the console. If the list is empty than
	 * "No authors were deleted." is displayed.
	 * 
	 * @param authors
	 *            to be printed
	 */
	public static void printdeletedAuthorsToConsole(final List<Author> authors) {
		if (authors.isEmpty()) {
			System.out.println("No authors were deleted.");
		} else {
			System.out.println("These author(s) were deleted:");
			for (final Author author : authors) {
				System.out.println(author.toString());
			}
		}
	}

	/**
	 * Prints the given publications to the console. If the list is empty than
	 * "No publications were deleted." is displayed.
	 * 
	 * @param publications
	 *            to be printed
	 */
	public static void printdeletedPublicationsToConsole(
			final List<Publication> publications) {
		if (publications.isEmpty()) {
			System.out.println("No publications were deleted.");
		} else {
			System.out.println("These publication(s) were deleted:");
			for (final Publication author : publications) {
				System.out.println(author.toString());
			}
		}
	}

	public static void main(final String[] args) {
		// TODO delete
		new DatabaseConfigurationGUI();
	}
}
