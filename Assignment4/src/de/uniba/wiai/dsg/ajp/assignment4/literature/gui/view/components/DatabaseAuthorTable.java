package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.AuthorConfigurationView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.tablemodels.AuthorTableModel;
/**
 * the first half of the main view. consists of the table and two buttons
 * (add/remove).
 * 
 * @author mathias
 * 
 */
public class DatabaseAuthorTable extends JPanel {

	private final LiteratureDatabaseController controller;
	/** serial version. */
	private static final long serialVersionUID = -6797113448778036171L;
	/** the scroll pane for the view. */
	private ScrollPane scrollPanel;
	/** the teble with the authors. */
	private final AuthorTableModel tableModel;
	/** table. */
	private JTable table;
	/**
	 * Constructor to initate the components.
	 * 
	 * @param controller
	 */
	public DatabaseAuthorTable(final LiteratureDatabaseController controller) {
		this.controller = controller;
		tableModel = new AuthorTableModel(controller.getAuthorList());
		initializeComponents();
	}
	/**
	 * Initializes the components.
	 */
	private void initializeComponents() {
		addKeyListener(new DataBaseViewKeyListner(controller));
		final JComponent buttonComp = new JPanel();
		buttonComp.addKeyListener(new DataBaseViewKeyListner(controller));
		buttonComp.setLayout(new GridLayout(1, 2));

		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected author");
		deleteSelectedAuthorButton.addKeyListener(new DataBaseViewKeyListner(
				controller));
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.removeAuthor();

			}

		});

		buttonComp.add(deleteSelectedAuthorButton, BorderLayout.CENTER);
		final Button addAuthorButton = new Button("Create Author");
		addAuthorButton.addKeyListener(new DataBaseViewKeyListner(controller));
		addAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new AuthorConfigurationView(controller);

			}

		});

		buttonComp.add(addAuthorButton, BorderLayout.CENTER);
		if (controller.getAuthorList().isEmpty()) {
			scrollPanel = new ScrollPane();
			scrollPanel.add(new JLabel("No authors added, yet!"));
			scrollPanel.addKeyListener(new DataBaseViewKeyListner(controller));
			setLayout(new GridLayout(2, 1));
			add(scrollPanel);
		} else {
			scrollPanel = new ScrollPane();
			table = new JTable(tableModel);
			scrollPanel.add(table);
			scrollPanel.addKeyListener(new DataBaseViewKeyListner(controller));
			setLayout(new GridLayout(2, 1));
			add(scrollPanel);
		}

		add(buttonComp);
	}

	/**
	 * Gets the selected row of the author table.
	 * 
	 * @return -1 when nothing is selected else the selected Row
	 */
	public int getSelectedRow() {
		if (table == null) {
			return -1;
		}
		if (table.getSelectedRowCount() > 1) {
			return -1;
		} else {
			return table.getSelectedRow();
		}
	}
}
