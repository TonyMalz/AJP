package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.MainController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.MenuController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.controller.MainViewWindowListener;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.MainModel;
/**
 * the main view.
 * 
 * @author tony
 * 
 */
public class MainView extends JFrame implements Observer {
	/** the main controller. */
	MainController controller;
	/** the main model. */
	MainModel model;
	/** the table with the author. */
	AuthorTableView authorTable;
	/** the table with the publications. */
	PublicationTableView publicationTable;
	/**
	 * Constructor.
	 * 
	 * @param mainController
	 *            main controller.
	 * @param model
	 *            main model.
	 */
	public MainView(final MainController mainController, final MainModel model) {
		super();
		controller = mainController;
		this.model = model;

		initObservables();
		initComponents();
	}
	/**
	 * sets the observables.
	 */
	private void initObservables() {
		if (model != null) {
			model.addObserver(this);

			if (model.getDatabase() != null) {
				model.getDatabase().addObserver(this);
			}
		}

	}

	@Override
	public void update(final Observable o, final Object arg) {
		if (o instanceof MainModel) {
			// database changed
			initObservables();
			resetTableComponents();
		}
		setTitle(getDatabaseName());
	}
	/**
	 * initiates the components.
	 */
	private void initComponents() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new MainViewWindowListener());
		setTitle(getDatabaseName());
		setSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setJMenuBar(getMainMenu());
		setContentPane(getMainContentPane());
	}
	/**
	 * get the name of the database.
	 * 
	 * @return the name.
	 */
	private String getDatabaseName() {
		final String path = model.getDatabase().getAbolutePath();
		return path != null ? path : "New Literature Database";
	}
	/**
	 * gets a new menu bar.
	 * 
	 * @return the menu view.
	 */
	private MenuView getMainMenu() {
		return new MenuView(new MenuController(model, this));
	}
	/**
	 * gets the actual tables with the buttons.
	 * 
	 * @return the components with tables and buttons
	 */
	private JPanel getMainContentPane() {
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(getAuthorPanel());
		panel.add(getPublicationPanel());

		return panel;
	}
	/**
	 * gets a new author table with the add/delete buttons.
	 * 
	 * @return the component
	 */
	private Component getAuthorPanel() {
		return authorTable = new AuthorTableView(model.getDatabase(),
				controller);
	}
	/**
	 * gets a new publication table with the add/remove buttons.
	 * 
	 * @return the component
	 */
	private Component getPublicationPanel() {
		return publicationTable = new PublicationTableView(model.getDatabase(),
				controller);
	}
	/**
	 * shows the standard error message.
	 * 
	 * @param message
	 *            to be displayed.
	 */
	public void showErrorMessage(final String message) {
		JOptionPane.showMessageDialog(this, message, "Sorry",
				JOptionPane.ERROR_MESSAGE);

	}
	/**
	 * resets the tables with the current contents.
	 */
	private void resetTableComponents() {
		authorTable.setModel(model.getDatabase());
		publicationTable.setModel(model.getDatabase());

	}
}
