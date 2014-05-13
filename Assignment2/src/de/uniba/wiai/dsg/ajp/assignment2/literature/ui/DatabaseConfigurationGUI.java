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
	 * 
	 * @throws LiteratureDatabaseException
	 */
	public DatabaseConfigurationGUI() throws LiteratureDatabaseException {
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
	 * @throws IOException
	 * @throws LiteratureDatabaseException
	 */
	private void subMenu() throws IOException, LiteratureDatabaseException {
		boolean runSubMenu = true;
		while (runSubMenu) {
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
				runSubMenu = false;
				break;
			}
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
	 * Prints the dataBase to a file. The user is asked for the path to be stred
	 * to.
	 * 
	 * @throws IOException
	 * @throws LiteratureDatabaseException
	 */
	private void printXMLFile() throws IOException, LiteratureDatabaseException {

		dataBaseService.saveXMLToFile();
	}

	/**
	 * Prints the dataBase to the console.
	 * 
	 * @throws LiteratureDatabaseException
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
	 * Removes a Publication from the DataBase. The user is asked for the id of
	 * the publication to be removed.
	 * 
	 * @throws IOException
	 * @throws LiteratureDatabaseException
	 */
	private void removePublication() throws IOException,
			LiteratureDatabaseException {
		final String id = consoleHelper
				.askNonEmptyString("Enter the ID of the Publication to be removed.");
		dataBaseService.removePublicationByID(id);

	}

	/**
	 * Adds a publication to the database. The user is asked one after another
	 * for the title, the published year, the type and the authors.
	 * 
	 * @throws LiteratureDatabaseException
	 * @throws IOException
	 */
	private void addPublication() throws LiteratureDatabaseException,
			IOException {
		final Publication pubToAdd = getNewPublication();
		dataBaseService.addPublication(pubToAdd.getTitle(), pubToAdd
				.getYearPublished(), pubToAdd.getType(), pubToAdd.getAuthors()
				.toArray(new Author[0]), pubToAdd.getId());

	}

	/**
	 * Removes a author from the database. The user is asked for the id of the
	 * author to be removed.
	 * 
	 * @throws LiteratureDatabaseException
	 * @throws IOException
	 */
	private void removeAuthor() throws LiteratureDatabaseException, IOException {
		final String id = consoleHelper
				.askNonEmptyString("Enter the ID of the Author to be removed.");
		dataBaseService.removeAuthorByID(id);

	}

	/**
	 * Adds a author to the database. The user is asked one after another for a
	 * name a email and an ID.
	 * 
	 * @throws LiteratureDatabaseException
	 * @throws IOException
	 */
	private void addAuthor() throws LiteratureDatabaseException, IOException {
		final Author authortoAdd = getNewAuthor();
		dataBaseService.addAuthor(authortoAdd.getName(),
				authortoAdd.getEmail(), authortoAdd.getId());
		// TODO publications????

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
	 * @throws IOException
	 */
	private Publication getNewPublication() throws IOException {
		final String title = consoleHelper
				.askNonEmptyString("Enter the title of the Publication:");
		final int year = consoleHelper.askIntegerInRange(
				"Enter published year:", 0, 2014);
		final PublicationType type = getPublicationType();
		final List<Author> authors = getAuthors();

		final Publication publication = new Publication();
		publication.setTitle(title);
		publication.setYearPublished(year);
		publication.setType(type);
		publication.setAuthors(authors);

		return publication;
	}

	/**
	 * Asks the user for a list of authors. He can: <br>
	 * (1) use an exiting one. Than he is asked for the id of that author. <br>
	 * (2) create a new Author. The user is than asked for the n name his email
	 * and a valid id. <br>
	 * (3) not add a new one
	 * 
	 * @return the list of authors
	 * @throws IOException
	 */
	private List<Author> getAuthors() throws IOException {
		System.out.println("(1) add a exiting author by ID");
		System.out.println("(2) Add a new author");
		final List<Author> authors = new ArrayList<Author>();
		int choice = consoleHelper.askIntegerInRange("", 1, 2);
		Author authorToAdd = null;
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

		while (true) {
			System.out.println("(1) add a exiting author by ID");
			System.out.println("(2) Add a new author");
			System.out.println("(0) No more authors");

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
		}

		return authors;
	}

	private PublicationType getPublicationType() throws IOException {
		while (true) {
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
		}

	}

	/**
	 * Asks the user for a new Author. <br>
	 * (1) the name (2) the email. must ba valid email <br>
	 * (3) ID. this must be a String that is not yet sed for another Author
	 * 
	 * @return
	 * @throws IOException
	 */
	private Author getNewAuthor() throws IOException {
		final String name = consoleHelper
				.askNonEmptyString("Enter the name of the Author:");
		final String id = getAuthorIDUnused();
		final String email = getEmail();

		final Author author = new Author();
		author.setName(name);
		author.setId(id);
		author.setEmail(email);
		return author;
	}

	/**
	 * Asks the user for a email address. if it is not valid he is asked again.
	 * 
	 * @return
	 * @throws IOException
	 */
	private String getEmail() throws IOException {
		while (true) {
			final String email = consoleHelper
					.askNonEmptyString("Enter a valid email:");
			if (!ValidationHelper.isEmail(email)) {
				// input is not a id
				System.out
						.println("The id entered is not a valid email please enter another one.");
			} else {
				return email;
			}
		}
	}

	/**
	 * asks the user for a new Id for an author. IF the id is already used in
	 * the dataBase than he is asked for another one.
	 * 
	 * @return the ID for the new Author
	 * @throws IOException
	 */
	private String getAuthorIDUnused() throws IOException {
		while (true) {
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
		}
	}

	private String getAuthorIDUsed() throws IOException {
		while (true) {
			final String id = consoleHelper
					.askNonEmptyString("Enter a ID that is not yet used:");
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
	 * loads and validates database by asking for path setSavePath to remember
	 * where to save this database
	 * 
	 * @throws LiteratureDatabaseException
	 * @throws IOException
	 */
	private void loadDatabase() throws LiteratureDatabaseException, IOException {
		String path = consoleHelper
				.askNonEmptyString("Enter a valid path to the database you want to load!");
		mainService.validate(path);
		dataBaseService = mainService.load(path);
		dataBaseService.setSavePath(path);

	}

	/**
	 * creates database by asking for save path setSavePath to remember where to
	 * save this database
	 * 
	 * @throws LiteratureDatabaseException
	 * @throws IOException
	 */
	private void createDatabase() throws LiteratureDatabaseException,
			IOException {
		String path = consoleHelper
				.askNonEmptyString("Enter a valid path to the database you want to load!");
		dataBaseService = mainService.create();
		dataBaseService.setSavePath(path);
		printXMLFile();

	}

	public static void main(final String[] args)
			throws LiteratureDatabaseException {
		new DatabaseConfigurationGUI();
	}

}
