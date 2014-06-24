package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model;

import javax.swing.table.AbstractTableModel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.PublicationModel;
/**
 * model of the table of the authors.
 * 
 * @author tony
 * 
 */
public class PublicationTableModel extends AbstractTableModel {
	/** the title of the columns. */
	private final String[] columnNames = {"ID", "Title", "Year", "Type",
			"Authors"};
	/** model of the publications. */
	private final PublicationModel model;
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            of the publications.
	 */
	public PublicationTableModel(final PublicationModel model) {
		this.model = model;
	}

	@Override
	public int getRowCount() {
		return model != null ? model.getPublications().length : 0;
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
		final Publication publication = model.getPublications()[rowIndex];
		switch (columnIndex) {
			case 0 :
				return publication.getId();
			case 1 :
				return publication.getTitle();
			case 2 :
				return publication.getYearPublished();
			case 3 :
				return publication.getType();
			case 4 :
				return getAuthors(publication);
		}
		return null;
	}
	/**
	 * builds an readable String of the authors of the publications. <br>
	 * Example: "Author1, Author2, Author3"
	 * 
	 * @param publication
	 *            to be builded from.
	 * @return the string
	 */
	private String getAuthors(final Publication publication) {
		String text = "";
		if (publication.getAuthors().size() > 0) {
			for (final Author author : publication.getAuthors()) {
				text += author.getId() + ", ";
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
