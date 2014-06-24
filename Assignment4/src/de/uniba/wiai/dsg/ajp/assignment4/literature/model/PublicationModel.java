package de.uniba.wiai.dsg.ajp.assignment4.literature.model;

import java.util.Observable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
/**
 * model for the publications.
 * 
 * @author tony
 * 
 */
public class PublicationModel extends Observable {
	/** the database. */
	DatabaseService database;
	/**
	 * Constructor.
	 * 
	 * @param database
	 *            to be used.
	 */
	public PublicationModel(final DatabaseService database) {
		this.database = database;
	}
	/**
	 * tries to add a new publication to the database..
	 * 
	 * @param publication
	 *            to be added
	 * @throws LiteratureDatabaseException
	 *             in case the format of the publication is not correct or the
	 *             id is already token.
	 */
	public void addPublication(final Publication publication)
			throws LiteratureDatabaseException {

		try {
			database.addPublication(publication.getTitle(),
					publication.getYearPublished(), publication.getType(),
					publication.getAuthors().toArray(new Author[0]),
					publication.getId());
			setChanged();
			notifyObservers(publication);
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
	}
	/**
	 * get the current publications
	 * 
	 * @return the publications
	 */
	public Publication[] getPublications() {
		return database.getPublications();
	}
	/**
	 * tries to remove the publication with the id.
	 * 
	 * @param id
	 *            of the publication to be removed.
	 * @throws LiteratureDatabaseException
	 *             if the id is invalid or not taken.
	 */
	public void removePublicationByID(final String id)
			throws LiteratureDatabaseException {

		try {
			database.removePublicationByID(id);
			setChanged();
			notifyObservers(id);
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
	}
}
