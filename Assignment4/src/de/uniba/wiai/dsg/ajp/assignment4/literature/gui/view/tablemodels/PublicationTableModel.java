package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;

public class PublicationTableModel extends AbstractTableModel {

	/** the serial version. */
	private static final long serialVersionUID = 5139168510042631059L;

	/** the columns. */
	private final String[] columnNames = {"ID", "Title", "Year", "Type",
			"Authors"};
	/** the publications for the table. */
	private final List<Publication> tableData;
	/** constructor. */
	public PublicationTableModel(final List<Publication> tableData) {
		this.tableData = tableData;
	}

	@Override
	public String getColumnName(final int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(final int arg0, final int arg1) {
		String authors = "";
		if (tableData.get(arg0).getAuthors().size() == 0) {
			authors = "---";
		} else {
			authors = tableData.get(arg0).getAuthors().get(0).getName();
			for (int i = 1; i < tableData.get(arg0).getAuthors().size(); i++) {
				authors += " and "
						+ tableData.get(arg0).getAuthors().get(i).getName();
			}
		}

		switch (arg1) {
			case 0 :
				return tableData.get(arg0).getId();
			case 1 :
				return tableData.get(arg0).getTitle();
			case 2 :
				return tableData.get(arg0).getYearPublished();
			case 3 :
				return tableData.get(arg0).getType();
			case 4 :
				return authors;
			default :
				return "---";
		}
	}

}
