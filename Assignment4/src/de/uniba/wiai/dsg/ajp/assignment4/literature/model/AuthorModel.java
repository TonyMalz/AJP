package de.uniba.wiai.dsg.ajp.assignment4.literature.model;

import java.util.Observable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
/**
 * model of the authors.
 * 
 * @author tony
 * 
 */
public class AuthorModel extends Observable {
	/** the database. */
	private final DatabaseService database;
	/**
	 * constructor.
	 * 
	 * @param database
	 *            to be used.
	 */
	public AuthorModel(final DatabaseService database) {
		this.database = database;
	}
	/**
	 * tries to add an author to the database.
	 * 
	 * @param author
	 *            to be added.
	 * @throws LiteratureDatabaseException
	 *             in case the format is incorrect or the id is already token
	 */
	public void addAuthor(final Author author)
			throws LiteratureDatabaseException {
		try {
			database.addAuthor(author.getName(), author.getEmail(),
					author.getId());
			setChanged();
			notifyObservers(author);
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
	}
	/**
	 * gets the author from the database.
	 * 
	 * @return the authors
	 */
	public Author[] getAuthors() {
		return database.getAuthors();
	}
	/**
	 * tries to remove the author with the id from the database.
	 * 
	 * @param id
	 *            of the author to be removed.
	 * @throws LiteratureDatabaseException
	 *             if the id is invalid or not token
	 */
	public void removeAuthorByID(final String id)
			throws LiteratureDatabaseException {
		try {
			database.removeAuthorByID(id);
			setChanged();
			notifyObservers(id);
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
	}
}
