package de.uniba.wiai.dsg.ajp.assignment4.literature.controller;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.CreatePublicationView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.MainView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.DatabaseModel;
/**
 * controller to create an new publication
 * 
 * @author tony
 * 
 */
public class CreatePublicationController {
	/** Model of the database. */
	DatabaseModel model;
	/** view to create new publication. */
	CreatePublicationView view;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            of the database.
	 * @param parentView
	 *            main view
	 */
	public CreatePublicationController(final DatabaseModel model,
			final MainView parentView) {
		this.model = model;
		view = new CreatePublicationView(parentView, this, model);
		view.setVisible(true);
	}
	/**
	 * tries to create a new publication. if it fails an error message is
	 * displayed.
	 * 
	 * @param publication
	 *            to be created
	 */
	public void createPublication(final Publication publication) {
		try {
			model.getPublicationModel().addPublication(publication);
			view.dispose();
		} catch (final LiteratureDatabaseException e) {
			view.showErrorMessage(e.getMessage());
		}
	}
}
