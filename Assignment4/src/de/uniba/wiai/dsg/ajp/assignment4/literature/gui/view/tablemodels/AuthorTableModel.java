package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;

public class AuthorTableModel extends AbstractTableModel {
	/** the serial version. */
	private static final long serialVersionUID = -2816434835774897063L;
	/** the columns. */
	private final String[] columnNames = {"ID", "Name", "E-Mail",
			"Publikationen"};
	/** the data of the table. */
	private final List<Author> tableData;
	/** constructor. */
	public AuthorTableModel(final List<Author> tableData) {
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
	public boolean isCellEditable(final int arg0, final int arg1) {
		return false;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(final int arg0, final int arg1) {
		String publications = "";
		if (tableData.get(arg0).getPublications().size() == 0) {
			publications = "---";
		} else {
			publications = tableData.get(arg0).getPublications().get(0)
					.getTitle();
			for (int i = 1; i < tableData.get(arg0).getPublications().size(); i++) {
				publications += " and "
						+ tableData.get(arg0).getPublications().get(i)
								.getTitle();
			}
		}

		switch (arg1) {
			case 0 :
				return tableData.get(arg0).getId();
			case 1 :
				return tableData.get(arg0).getName();
			case 2 :
				return tableData.get(arg0).getEmail();
			case 3 :
				return publications;
			default :
				return "---";
		}
	}

}
