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
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model.AuthorTableModel;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.DatabaseModel;
/**
 * view of the table of the authors with the delete/add button.
 * 
 * @author tony
 * 
 */
public class AuthorTableView extends JPanel implements Observer {
	/** the table with the authors. */
	JTable authorTable;
	/** button to delete. */
	JButton deleteBtn;
	/** model of the database. */
	DatabaseModel model;
	/** the main controller. */
	MainController controller;
	/**
	 * Constuctor.
	 * 
	 * @param model
	 *            of the database
	 * @param controller
	 *            main controller.
	 */
	public AuthorTableView(final DatabaseModel model,
			final MainController controller) {
		super();
		this.model = model;
		this.controller = controller;

		initComponents();
		initObservables();
		updateComponents();
	}
	/**
	 * setup of the observers.
	 */
	private void initObservables() {
		if (model != null) {
			model.getAuthorModel().addObserver(this);
			model.getPublicationModel().addObserver(this);
		}
	}
	/**
	 * creates and centers the components.
	 */
	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder("Authors"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getAuthorTable());
		add(getAuthorButtons());
	}
	/**
	 * creates the table of the authors in a scroll pane.
	 * 
	 * @return the finished component.
	 */
	private Component getAuthorTable() {
		authorTable = new JTable();
		authorTable.getSelectionModel().addListSelectionListener(
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
		authorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JScrollPane jScrollPane = new JScrollPane(authorTable);
		return jScrollPane;
	}
	/**
	 * creates the add/delete button.
	 * 
	 * @return the component with the two buttons
	 */
	private JPanel getAuthorButtons() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		deleteBtn = new JButton("Delete selected author");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				removeSelectedAuthor();
			}
		});
		panel.add(deleteBtn);

		final JButton b = new JButton("Create author");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.createAuthor();
			}
		});
		panel.add(b);

		return panel;
	}
	/**
	 * sets a new model and notifies the observers.
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
	 * updates the table and disables the delete button.
	 */
	private void updateComponents() {
		authorTable.setModel(new AuthorTableModel(model.getAuthorModel()));
		deleteBtn.setEnabled(false);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		updateComponents();
	}
	private void removeSelectedAuthor() {
		final int selectedRow = authorTable.getSelectedRow();
		if (selectedRow >= 0) {
			final String authorId = (String) authorTable.getValueAt(
					selectedRow, 0);
			controller.deleteAuthor(authorId);
		}
	}
}
