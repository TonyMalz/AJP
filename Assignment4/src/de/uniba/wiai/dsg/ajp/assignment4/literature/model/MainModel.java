package de.uniba.wiai.dsg.ajp.assignment4.literature.model;

import java.nio.file.Paths;
import java.util.Observable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.impl.MainServiceImpl;
/**
 * model of the main controller / view.
 * 
 * @author tony
 * 
 */
public class MainModel extends Observable {
	/** helper class for setting up a new, loading and validating a database. */
	private final MainService mainService;
	/** the datbase model. */
	private DatabaseModel databaseModel;
	/** Constructor. */
	public MainModel() {
		mainService = new MainServiceImpl();
	}
	/**
	 * @return the databaseModel
	 */
	public DatabaseModel getDatabase() {
		return databaseModel;
	}
	/**
	 * tries to create a new database.
	 * 
	 * @return the database model with the new and empty database.
	 * @throws LiteratureDatabaseException
	 *             in cae an error occurs while creating a new database.
	 */
	public DatabaseModel createNewDatabase() throws LiteratureDatabaseException {
		try {
			databaseModel = new DatabaseModel(mainService.create());

			setChanged();
			notifyObservers();
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
		return databaseModel;
	}
	/**
	 * tries to load an existing database.
	 * 
	 * @param path
	 *            to be loaded from
	 * @return the database model with the loaded database.
	 * @throws LiteratureDatabaseException
	 *             in case an error occurs while trying to load the database.
	 */
	public DatabaseModel loadDatabase(final String path)
			throws LiteratureDatabaseException {
		try {
			databaseModel = new DatabaseModel(mainService.load(path));
			databaseModel.setPath(Paths.get(path));

			setChanged();
			notifyObservers();
		} catch (final LiteratureDatabaseException e) {
			throw e;
		}
		return databaseModel;
	}
	/**
	 * tries to validate the database.
	 * 
	 * @param path
	 *            to the xml-database to be validated.
	 * @throws LiteratureDatabaseException
	 *             in case of error while validating
	 */
	public void validateDatabase(final String path)
			throws LiteratureDatabaseException {
		mainService.validate(path);
	}
}
