package de.uniba.wiai.dsg.ajp.assignment4.literature.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
/**
 * the database model.
 * 
 * @author tony
 * 
 */
public class DatabaseModel extends Observable {
	/** the database. */
	private final DatabaseService database;
	/** save path. */
	private Path path;
	/** the author model. */
	private AuthorModel authorModel;
	/** the publication model. */
	private PublicationModel publicationModel;
	/**
	 * constructor.
	 * 
	 * @param database
	 *            to be used.
	 */
	public DatabaseModel(final DatabaseService database) {
		this.database = database;
		authorModel = new AuthorModel(database);
		publicationModel = new PublicationModel(database);
	}
	/**
	 * tries to save the database to XML
	 * 
	 * @param path
	 *            to be saved to.
	 * @throws LiteratureDatabaseException
	 *             in case: <br>
	 *             there are errors while converting the database.<br>
	 *             the path is invallid
	 */
	public void save(final Path path) throws LiteratureDatabaseException {
		try {
			String fileName = path.toString();
			if (!fileName.endsWith(".xml")) {
				fileName += ".xml";
			}

			database.saveXMLToFile(fileName);
			this.path = Paths.get(fileName);

			setChanged();
			notifyObservers();
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
	}
	/**
	 * 
	 * @param path
	 *            to be set
	 */
	public void setPath(final Path path) {
		this.path = path;
	}
	/**
	 * get the path as a string
	 * 
	 * @return the path as string or null when path is null
	 */
	public String getAbolutePath() {
		return path != null ? path.toAbsolutePath().toString() : null;
	}
	/**
	 * 
	 * @return the authorModel
	 */
	public AuthorModel getAuthorModel() {
		return authorModel;
	}
	/**
	 * 
	 * @param authorModel
	 *            to be set.
	 */
	public void setAuthorModel(final AuthorModel authorModel) {
		this.authorModel = authorModel;
	}
	/**
	 * 
	 * @return the publicationModel
	 */
	public PublicationModel getPublicationModel() {
		return publicationModel;
	}
	/**
	 * 
	 * @param publicationModel
	 *            to be set.
	 */
	public void setPublicationModel(final PublicationModel publicationModel) {
		this.publicationModel = publicationModel;
	}

}
