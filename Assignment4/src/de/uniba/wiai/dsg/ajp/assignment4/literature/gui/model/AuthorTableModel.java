package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model;

import javax.swing.table.AbstractTableModel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.AuthorModel;
/**
 * model of the table of the author.
 * 
 * @author tony
 * 
 */
public class AuthorTableModel extends AbstractTableModel {
	/** the titles of the columns. */
	private final String[] columnNames = {"ID", "Name", "Email", "Publications"};
	/** the author model. */
	private final AuthorModel model;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            of the authors.
	 */
	public AuthorTableModel(final AuthorModel model) {
		this.model = model;
	}

	@Override
	public int getRowCount() {
		return model != null ? model.getAuthors().length : 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Author author = model.getAuthors()[rowIndex];
		switch (columnIndex) {
			case 0 :
				return author.getId();
			case 1 :
				return author.getName();
			case 2 :
				return author.getEmail();
			case 3 :
				return getPublications(author);
		}
		return null;
	}
	/**
	 * get the publications of the author as an String.<br>
	 * example. "Pub1, Pub2, Pub3"
	 * 
	 * @param author
	 *            of the publications
	 * @return the publications as an readable String
	 */
	private String getPublications(final Author author) {
		String text = "";
		if (author.getPublications().size() > 0) {
			for (final Publication pub : author.getPublications()) {
				text += pub.getId() + ", ";
			}
			text = text.substring(0, text.length() - 2);
		}
		return text;
	}

	@Override
	public boolean isCellEditable(final int row, final int col) {
		return false;
	}

}
