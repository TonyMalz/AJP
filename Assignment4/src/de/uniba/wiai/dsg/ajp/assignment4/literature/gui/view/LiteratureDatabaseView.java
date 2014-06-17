/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import com.sun.istack.internal.logging.Logger;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DataBaseAuthorTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabaseMenuBar;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabasePublicationTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;

/**
 * The view for manipulating a database.
 * 
 * @author mathias
 * 
 */
public class LiteratureDatabaseView extends JFrame {
	/** default logger. */
	private final static Logger LOGGER = Logger
			.getLogger(LiteratureDatabaseView.class);
	/** serial version. */
	private static final long serialVersionUID = -30430229188690490L;
	/**
	 * the first half of the view contains the table and two buttons(remove/add
	 * author).
	 */
	private final JComponent authorComponenet;
	/**
	 * the second half of the view contains the table and two buttons(remove/add
	 * publication).
	 */
	private final JComponent publicationComponent;
	/** the menu with the choices new, load,save As,exit and shortcuts . */
	private final JMenuBar menu;
	/**
	 * Constructor of the view. initiates the components.
	 */
	public LiteratureDatabaseView() {
		authorComponenet = new DataBaseAuthorTable();
		publicationComponent = new DatabasePublicationTable();
		menu = new DatabaseMenuBar();
		setLayout(new GridLayout(2, 1));
		this.add(authorComponenet);
		this.add(publicationComponent);
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new DataBaseViewKeyListner());
		pack();
		validate();
		setVisible(true);
	}
	/**
	 * starts the view to adda new Author.
	 */
	public static void showAddAuthorView() {
		LOGGER.info("Shoming author config view");
		new AuthorConfigurationView();

	}
	/**
	 * Creates a new database.
	 */
	public static void createDatabase() {
		// TODO Auto-generated method stub
		LOGGER.info("Creating new Datbase.");

	}
	/**
	 * Starts the view to add a new publication.
	 */
	public static void showAddPublication() {
		LOGGER.info("Showing add Publication view");
		new PublicationConfigurationView(null);

	}
	/**
	 * Adds a new author to the database.
	 * 
	 * @param authorToAdd
	 *            author to be added.
	 */
	public static void createAuthor(final Author authorToAdd) {
		// TODO Auto-generated method stub
		showInfoMessage("Adding the author: " + authorToAdd.toString(),
				"Just for testing");
		LOGGER.info("Adding author: " + authorToAdd.toString());
	}
	/**
	 * Adds a new publication to the database.
	 * 
	 * @param pubToAdd
	 *            publication to be added
	 */
	public static void createPublication(final Publication pubToAdd) {
		// TODO add connection to controller to add a new Author
		showInfoMessage("Adding the author: " + pubToAdd.toString(),
				"Just for testing");
		LOGGER.info("Adding Publication: " + pubToAdd.toString());

	}
	/**
	 * shows the Standard error message.
	 * 
	 * @param message
	 *            to be displayed
	 * @param title
	 *            of the frame
	 */
	public static void showErrorMessage(final String message, final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * shows the Standard info message.
	 * 
	 * @param message
	 *            to be displayed
	 * @param title
	 *            of the frame
	 */
	public static void showInfoMessage(final String message, final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * removes an authro from the datbase.
	 * 
	 * @param authorToRemove
	 *            author to be removed.
	 */
	public static void removeAuthor(final Author authorToRemove) {
		// TODO Auto-generated method stub
		LOGGER.info("Removing Author: " + authorToRemove);

	}
	/**
	 * removes the publication from the database.
	 * 
	 * @param pubToRemove
	 *            publication to be removed.
	 */
	public static void removePublication(final Publication pubToRemove) {
		// TODO Auto-generated method stub
		LOGGER.info("Removing Publication: " + pubToRemove);

	}

	/**
	 * Loads an exiting database.
	 */
	public static void loadDatabase() {
		// TODO Auto-generated method stub
		LOGGER.info("Loading database.");

	}
	/**
	 * saves the current database.
	 */
	public static void saveDatabaseAs() {
		// TODO Auto-generated method stub
		LOGGER.info("Saving database.");

	}
	/**
	 * exits from the view.
	 */
	public static void exit() {
		// TODO Auto-generated method stub
		// TODO dialog for saving?
		LOGGER.info("EXIT");

	}
	/**
	 * shows a new frame with the shortcuts of the main menu.
	 */
	public static void showShortcuts() {
		final JFrame helpFrame = new JFrame("Shortcuts");
		helpFrame.setLayout(new GridLayout(0, 1));
		final String[] content = DataBaseViewKeyListner.getHelp();
		helpFrame.add(new JLabel(
				"Use can use these Shortcuts in the main menu:"));
		for (final String shortcut : content) {
			helpFrame.add(new JLabel(shortcut));
		}
		final Button okButton = new Button("OKAY");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				helpFrame.dispose();
			}
		});
		helpFrame.add(okButton);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.pack();
		helpFrame.validate();
		helpFrame.setVisible(true);
	}
	public static void main(final String[] args) {
		new LiteratureDatabaseView();
		// TODO remove
	}

}
