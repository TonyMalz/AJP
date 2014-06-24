package de.uniba.wiai.dsg.ajp.assignment4.literature.controller;

import java.io.File;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.MainView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.MenuView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.MainModel;
/**
 * Controller of the menu bar.
 * 
 * @author tony
 * 
 */
public class MenuController {
	/** the main model. */
	MainModel model;
	/** view of the menu bar. */
	MenuView view;
	/** the main view. */
	MainView parentView;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            the main model.
	 * @param parentView
	 *            the main view.
	 */
	public MenuController(final MainModel model, final MainView parentView) {
		this.model = model;
		this.parentView = parentView;
		view = new MenuView(this);

	}
	/**
	 * tries to create a new database. if it fails an error message is
	 * displayed.
	 */
	public void newDatabase() {

		// TODO maybe you're sure dialog when entries exist?
		try {
			model.createNewDatabase();
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}
	/**
	 * tries to load an existing database.
	 */
	public void loadDatabase() {

		// TODO maybe you're sure dialog when entries exist?
		view.showLoadDatabaseDialog();
	}
	/**
	 * tries to save an database.
	 */
	public void saveDatabase() {
		view.showSaveDatabaseDialog();
	}
	/**
	 * shows a window asking if the user wants to exit. When confirmed the
	 * program is exited.
	 */
	public void exit() {
		if (view.exitDialog()) {
			parentView.dispose();
		}
	}
	/**
	 * tries to load an existing database from a given file. shows an error
	 * message in case of error.
	 * 
	 * @param selectedFile
	 *            file to be loaded from.
	 */
	public void loadDatabase(final File selectedFile) {
		try {
			model.validateDatabase(selectedFile.toString());
			model.loadDatabase(selectedFile.toString());
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}

	}
	/**
	 * saves the database to a specific file. shows an error message in case of
	 * error.
	 * 
	 * @param selectedFile
	 *            file to saved to
	 */
	public void saveDatabase(final File selectedFile) {
		try {
			model.getDatabase().save(selectedFile.toPath());
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}

	}
}
