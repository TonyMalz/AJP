package de.uniba.wiai.dsg.ajp.assignment4.literature.models;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.impl.MainServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;

public class LiteratureDatabaseModel extends Observable {
	/** database service. */
	DatabaseService databaseService;
	/** service for creating a new database. */
	MainService mainService;
	/** Constructor. */
	public LiteratureDatabaseModel() {
		mainService = new MainServiceImpl();
		createDatabase();
	}
	/**
	 * tries to create a new and empty database.
	 */
	public void createDatabase() {
		try {
			databaseService = mainService.create();
			setChanged();
			notifyObservers();
		} catch (final LiteratureDatabaseException e) {
			// TODO give error massage to view
			e.printStackTrace();
		}

	}
	/**
	 * tries to load a new database.
	 * 
	 * @param file
	 *            to be loaded from
	 */
	public void loadDatabase(final File file) {
		if (file == null) {
			// TODO error or nothing?
		} else {
			try {
				mainService.validate(file.getPath());
				databaseService = mainService.load(file.getPath());
				setChanged();
				notifyObservers();
			} catch (final LiteratureDatabaseException e) {
				// TODO give error massage to view
				e.printStackTrace();
			}
		}
	}
	/**
	 * tries to save the database to a specific file.
	 * 
	 * @param file
	 *            to be saved to
	 */
	public void saveDatabaseAs(final File file) {
		if (file == null) {
			// TODO error or nothing?
		} else {
			try {
				String fileName = file.toString();
				if (!fileName.endsWith(".xml")) {
					fileName += ".xml";
				}
				databaseService.saveXMLToFile(fileName);
				// TODO: necessary?
				// setChanged();
				// notifyObservers();
			} catch (final LiteratureDatabaseException e) {
				// TODO give error massage to view
				e.printStackTrace();
			}
		}

	}
	/**
	 * Adds a new author to the database.
	 * 
	 * @param authorToAdd
	 *            author to be added.
	 * @throws LiteratureDatabaseException
	 *             in case the id is already token.
	 */
	public void createAuthor(final Author authorToAdd)
			throws LiteratureDatabaseException {

		databaseService.addAuthor(authorToAdd.getName(),
				authorToAdd.getEmail(), authorToAdd.getId());
		setChanged();
		notifyObservers();
	}
	/**
	 * Adds a new publication to the database.
	 * 
	 * @param authpubToAdd
	 *            publication to be added.
	 * @throws LiteratureDatabaseException
	 *             in case the id is already token.
	 */
	public void createPublication(final Publication pubToAdd)
			throws LiteratureDatabaseException {
		databaseService.addPublication(pubToAdd.getTitle(), pubToAdd
				.getYearPublished(), pubToAdd.getType(), pubToAdd.getAuthors()
				.toArray(new Author[pubToAdd.getAuthors().size()]), pubToAdd
				.getId());
		setChanged();
		notifyObservers();

	}
	/**
	 * removes an author from the database.
	 * 
	 * @param selectedAuthorRow
	 *            row index of the selected author to be deleted
	 */
	public void removeAuthor(final int selectedAuthorRow) {
		if (selectedAuthorRow < 0) {
			// TODO give error massage to view
		} else {
			try {
				databaseService
						.removeAuthorByID(databaseService.getAuthors()[selectedAuthorRow]
								.getId());
			} catch (final LiteratureDatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setChanged();
			notifyObservers();
		}
	}
	/**
	 * removes an publication from the database.
	 * 
	 * @param selectedPublicationRow
	 *            row index of the publcation to be deleted.
	 */
	public void removePublication(final int selectedPublicationRow) {
		if (selectedPublicationRow < 0) {
			// TODO give error massage to view
		} else {
			try {
				databaseService.removePublicationByID(databaseService
						.getPublications()[selectedPublicationRow].getId());
				setChanged();
				notifyObservers();
			} catch (final LiteratureDatabaseException e) {
				// TODO give error massage to view
				e.printStackTrace();
			}
		}
	}
	/**
	 * Get a list of the authors.
	 * 
	 * @return a list of the current authors
	 */
	public List<Author> getAuthorList() {
		if (databaseService == null) {
			return null;
		}
		return Arrays.asList(databaseService.getAuthors());
	}
	/**
	 * Get a list of the publications
	 * 
	 * @return a list of the current publications.
	 */
	public List<Publication> getPublicationList() {
		if (databaseService == null) {
			return null;
		}
		return Arrays.asList(databaseService.getPublications());
	}
	/**
	 * get the current authors.
	 * 
	 * @return authors.
	 */
	public Author[] getAuthorArray() {
		if (databaseService == null) {
			return null;
		}
		return databaseService.getAuthors();
	}
}
