package de.uniba.wiai.dsg.ajp.assignment4.literature.controller;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.CreateAuthorView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.MainView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.AuthorModel;
/**
 * Controller to add a new author.
 * 
 * @author tony
 * 
 */
public class CreateAuthorController {
	/** model of authors. */
	AuthorModel model;
	/** view to create a new author. */
	CreateAuthorView view;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            of the authors.
	 * @param parentView
	 *            main view
	 */
	public CreateAuthorController(final AuthorModel model,
			final MainView parentView) {
		this.model = model;
		view = new CreateAuthorView(parentView, this);
		view.setVisible(true);
	}
	/**
	 * tries to create new author. when it fails an error message is displayed.
	 * 
	 * @param author
	 *            to be created.
	 */
	public void createAuthor(final Author author) {
		try {
			model.addAuthor(author);
			view.dispose();
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}
}
