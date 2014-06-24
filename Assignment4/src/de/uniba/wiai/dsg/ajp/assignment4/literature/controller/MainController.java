package de.uniba.wiai.dsg.ajp.assignment4.literature.controller;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.MainView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.MainModel;
/**
 * controller for the main view.
 * 
 * @author tony
 * 
 */
public class MainController {
	/** model which holds the current database */
	private final MainModel model;
	/** the main view for manipulating the database. */
	private MainView view;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            of the database
	 */
	public MainController(final MainModel model) {
		this.model = model;
		initDatabase();
		setupWindow();
	}
	/**
	 * generates a new view.
	 */
	private void setupWindow() {
		view = new MainView(this, model);
		view.setVisible(true);
	}
	/**
	 * tries to setup a new and empty database. if it fails an error message is
	 * displayed.
	 */
	private void initDatabase() {
		try {
			model.createNewDatabase();
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}
	/**
	 * Generates a new controller to create a new author. this will display a
	 * new view.
	 */
	public void createAuthor() {
		new CreateAuthorController(model.getDatabase().getAuthorModel(), view);
	}
	/**
	 * Generates a new controller to create a new publication. this will display
	 * a new view.
	 */
	public void createPublication() {
		new CreatePublicationController(model.getDatabase(), view);
	}

	/**
	 * tries to remove a publication from the database. if it fails an error
	 * message is displayed.
	 * 
	 * @param pubId
	 *            id of the publication to be removed
	 */
	public void deletePublication(final String pubId) {
		try {
			model.getDatabase().getPublicationModel()
					.removePublicationByID(pubId);
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}
	/**
	 * tries to remove an author from the database. if it fails an error message
	 * is displayed.
	 * 
	 * @param authorId
	 *            id of the author to be removed.
	 */
	public void deleteAuthor(final String authorId) {
		try {
			model.getDatabase().getAuthorModel().removeAuthorByID(authorId);
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}

}
