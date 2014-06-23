package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.PublicationConfigurationView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.tablemodels.PublicationTableModel;
/**
 * the second half of the main view. consists of the table and two buttons
 * (add/remove).
 * 
 * @author mathias
 * 
 */
public class DatabasePublicationTable extends JPanel {

	private final LiteratureDatabaseController controller;
	/** serial version. */
	private static final long serialVersionUID = -1096171845816582557L;

	/** the scroll pane for the view. */
	private ScrollPane scrollPanel;
	/** the teble with the pubication. */
	private final PublicationTableModel tableModel;
	/** table. */
	private JTable table;
	/**
	 * Constructor to initate the components.
	 * 
	 * @param controller
	 */
	public DatabasePublicationTable(
			final LiteratureDatabaseController controller) {
		this.controller = controller;
		tableModel = new PublicationTableModel(controller.getPublicationList());
		initializeComponents();
	}
	/**
	 * Initializes the components.
	 */
	private void initializeComponents() {
		addKeyListener(new DataBaseViewKeyListner(controller));
		final JPanel buttonsComp = new JPanel();
		buttonsComp.addKeyListener(new DataBaseViewKeyListner(controller));
		buttonsComp.setLayout(new GridLayout(1, 2));

		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected publication");
		deleteSelectedAuthorButton.addKeyListener(new DataBaseViewKeyListner(
				controller));
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.removePublication();

			}

		});

		buttonsComp.add(deleteSelectedAuthorButton);
		final Button addPubButton = new Button("Create Publication");
		addPubButton.addKeyListener(new DataBaseViewKeyListner(controller));
		addPubButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new PublicationConfigurationView(controller.getAuthorArray(),
						controller);

			}

		});

		buttonsComp.add(addPubButton);
		if (controller.getPublicationList().isEmpty()) {
			scrollPanel = new ScrollPane();
			scrollPanel.add(new JLabel("No publications added, yet!"));
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

		add(buttonsComp);
	}
	/**
	 * get the selected row.
	 * 
	 * @return -1 when nothing is selected, the selected row otherwise
	 */
	public int getSelectedRow() {
		if (table == null) {
			return -1;
		} else if (table.getSelectedRowCount() > 1) {
			return -1;
		} else {
			return table.getSelectedRow();
		}
	}

}
