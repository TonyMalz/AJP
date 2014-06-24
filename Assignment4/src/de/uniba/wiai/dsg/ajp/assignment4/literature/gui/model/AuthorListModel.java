package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.AuthorModel;
/**
 * List of the selectable authors when creating a new publication.
 * 
 * @author tony
 * 
 */
public class AuthorListModel implements ListModel<String> {
	/** the authors. */
	AuthorModel model;
	/**
	 * Constructor
	 * 
	 * @param model
	 *            of the authors.
	 */
	public AuthorListModel(final AuthorModel model) {
		this.model = model;
	}

	@Override
	public int getSize() {
		return model.getAuthors().length;
	}

	@Override
	public String getElementAt(final int index) {
		final Author author = model.getAuthors()[index];
		return author.getName() + " (" + author.getId() + ")";
	}

	@Override
	public void addListDataListener(final ListDataListener l) {
	}

	@Override
	public void removeListDataListener(final ListDataListener l) {
	}

}
