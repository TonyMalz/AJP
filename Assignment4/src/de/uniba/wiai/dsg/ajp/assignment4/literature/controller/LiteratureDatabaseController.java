package de.uniba.wiai.dsg.ajp.assignment4.literature.controller;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.LiteratureDatabaseView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.models.LiteratureDatabaseModel;

public class LiteratureDatabaseController implements Observer {
	/** model. */
	LiteratureDatabaseModel model;
	/** view. */
	LiteratureDatabaseView view;
	/**
	 * Constructor needs a model.
	 * 
	 * @param model
	 *            for the controller.
	 */
	public LiteratureDatabaseController(final LiteratureDatabaseModel model) {
		this.model = model;
		view = new LiteratureDatabaseView(model, this);
		model.addObserver(this);
	}

	@Override
	public void update(final Observable arg0, final Object arg1) {
	}
	/**
	 * Loads a exiting xml-database to the model
	 * 
	 * @param file
	 */
	public void loadDatabaseDestination(final File file) {
		model.loadDatabase(file);

	}
	/**
	 * get the current authors.
	 * 
	 * @return the authors.
	 */
	public List<Author> getAuthorList() {
		return model.getAuthorList();
	}
	/**
	 * get the current publications
	 * 
	 * @return the publications
	 */
	public List<Publication> getPublicationList() {
		return model.getPublicationList();
	}
	/**
	 * adds a new author to the model shows an error message in case of error
	 * 
	 * @param authorToAdd
	 *            author to be added
	 */
	public void createAuthor(final Author authorToAdd) {
		try {
			model.createAuthor(authorToAdd);
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage(), "ERROR");
		}

	}
	/**
	 * adds a new publication to the model shows an error message in case of
	 * error
	 * 
	 * @param pubToAdd
	 *            author to be added
	 */
	public void createPublication(final Publication pubToAdd) {
		try {
			model.createPublication(pubToAdd);
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage(), "ERROR");
		}

	}
	/**
	 * removes the selected author from the database.
	 */
	public void removeAuthor() {
		model.removeAuthor(view.getSelectedAuthorRow());
	}
	/**
	 * removes the selected publication from the database.
	 */
	public void removePublication() {
		model.removePublication(view.getSelectedPublicationRow());
	}
	/**
	 * saves the database to XML-File
	 * 
	 * @param file
	 *            to be saved to
	 */
	public void saveDatabaseAsDestination(final File file) {
		model.saveDatabaseAs(file);

	}
	/**
	 * Shows an exit dialog and exits immediately when confirmed.
	 */
	public void exit() {
		if (view.exitDialog()) {
			System.exit(0);
		}

	}
	/**
	 * shows a window with the current shortcuts.
	 */
	public void showShortcuts() {
		view.showShortcuts();

	}
	/**
	 * Creates a new and empty database.
	 */
	public void createDatabase() {
		model.createDatabase();

	}
	/**
	 * get the authors as an array
	 * 
	 * @return the authors
	 */
	public Author[] getAuthorArray() {
		return model.getAuthorArray();
	}
	/**
	 * loads a database.
	 */
	public void loadDatabase() {
		view.loadDatabase();

	}
	/**
	 * Asks the user for a save Location and saves the database there.
	 */
	public void saveDatabaseAs() {
		view.saveDatabaseAs();

	}
	/**
	 * view to create a new author
	 */
	public void showAddAuthorView() {
		view.showAddAuthorView();

	}
	/**
	 * view to create a new publication.
	 */
	public void showAddPublication() {
		view.showAddPublication();

	}
	/**
	 * removes a author or publication.
	 */
	public void removeAuthorOrPublication() {
		final int selectedAuthorRow = view.getSelectedAuthorRow();
		final int selectedPublicationRow = view.getSelectedPublicationRow();

		if (selectedAuthorRow >= 0 & selectedPublicationRow >= 0) {
			// TODO: Error, too much rows chosen
		} else if (selectedAuthorRow == -1 & selectedPublicationRow == -1) {
			// TODO: Error, no row chosen
		} else if (selectedAuthorRow >= 0) {
			if (view.removeDialog()) {
				model.removeAuthor(selectedAuthorRow);
			}
		} else {
			if (view.removeDialog()) {
				model.removePublication(selectedPublicationRow);
			}
		}

	}
}
