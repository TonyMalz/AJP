package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.MainController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model.PublicationTableModel;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.AuthorModel;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.DatabaseModel;
/**
 * component with publication table and delete/add button
 * 
 * @author tony
 * 
 */
public class PublicationTableView extends JPanel implements Observer {
	/** table with publications. */
	JTable publicationTable;
	/** delete button. */
	JButton deleteBtn;
	/** add button. */
	JButton createBtn;
	/** main model. */
	DatabaseModel model;
	/** main controller. */
	MainController controller;
	/**
	 * constructor.
	 * 
	 * @param model
	 *            main model
	 * @param controller
	 *            main controller
	 */
	public PublicationTableView(final DatabaseModel model,
			final MainController controller) {
		super();
		this.model = model;
		this.controller = controller;

		initComponents();
		initObservables();
		updateComponents();
	}
	/**
	 * sets the observables.
	 */
	private void initObservables() {
		if (model != null) {
			model.getPublicationModel().addObserver(this);
			model.getAuthorModel().addObserver(this);
		}
	}
	/**
	 * generates the components.
	 */
	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder("Publications"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(getPublicationTable());
		add(getPublicationButtons());
	}
	/**
	 * gets the table with the publications
	 * 
	 * @return the component
	 */
	private Component getPublicationTable() {
		publicationTable = new JTable();
		publicationTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(final ListSelectionEvent e) {
						if (((DefaultListSelectionModel) e.getSource())
								.isSelectionEmpty()) {
							deleteBtn.setEnabled(false);
						} else {
							deleteBtn.setEnabled(true);
						}
					}
				});
		publicationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return new JScrollPane(publicationTable);
	}
	/**
	 * sets the component with the buttons up.
	 * 
	 * @return the component with the add/remove buttons.
	 */
	private JPanel getPublicationButtons() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		deleteBtn = new JButton("Delete selected publication");
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				final int selectedRow = publicationTable.getSelectedRow();
				if (selectedRow > -1) {
					final String pubId = (String) publicationTable.getValueAt(
							selectedRow, 0);

					controller.deletePublication(pubId);
				}
			}
		});
		panel.add(deleteBtn);

		createBtn = new JButton("Create publication");
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.createPublication();
			}
		});
		panel.add(createBtn);
		return panel;
	}
	/**
	 * sets the model and notifies the observers.
	 * 
	 * @param model
	 *            to be set.
	 */
	public void setModel(final DatabaseModel model) {
		this.model = model;
		initObservables();
		updateComponents();
	}
	/**
	 * updates the components and disables the delete button.
	 */
	private void updateComponents() {
		publicationTable.setModel(new PublicationTableModel(model
				.getPublicationModel()));
		createBtn.setEnabled(isCreateBtnClickable(model.getAuthorModel()));
		deleteBtn.setEnabled(false);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		updateComponents();
	}
	/**
	 * checks if the create publication button is clickable.
	 * 
	 * @param model
	 *            to be checked from
	 * @return true when there is at last one author, false otherwise.
	 */
	private boolean isCreateBtnClickable(final AuthorModel model) {
		return model.getAuthors().length > 0;
	}

}
