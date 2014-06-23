/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabaseAuthorTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabaseMenuBar;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabasePublicationTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.models.LiteratureDatabaseModel;

/**
 * The view for manipulating a database.
 * 
 * @author mathias
 * 
 */
public class LiteratureDatabaseView extends JFrame implements Observer {

	/** serial version. */
	private static final long serialVersionUID = -30430229188690490L;

	private final LiteratureDatabaseController controller;
	private final LiteratureDatabaseModel model;

	/**
	 * the first half of the view contains the table and two buttons(remove/add
	 * author).
	 */
	private JComponent authorComponent;
	/**
	 * the second half of the view contains the table and two buttons(remove/add
	 * publication).
	 */
	private JComponent publicationComponent;
	/** the menu with the choices new, load,save As,exit and shortcuts . */
	private JMenuBar menu;
	/**
	 * Constructor of the view. initiates the components.
	 */
	public LiteratureDatabaseView(final LiteratureDatabaseModel model,
			final LiteratureDatabaseController controller) {

		this.controller = controller;
		this.model = model;
		model.addObserver(this);

		authorComponent = new DatabaseAuthorTable(controller);
		publicationComponent = new DatabasePublicationTable(controller);
		menu = new DatabaseMenuBar(controller);
		setLayout(new GridLayout(2, 1));
		this.add(authorComponent);
		this.add(publicationComponent);
		setJMenuBar(menu);
		addWindowListener(new DatabaseViewWindowListner(controller));
		setTitle("New Literature Database");
		addKeyListener(new DataBaseViewKeyListner(controller));
		pack();
		validate();
		setVisible(true);
	}
	/**
	 * starts the view to adda new Author.
	 */
	public void showAddAuthorView() {

		new AuthorConfigurationView(controller);

	}

	/**
	 * Starts the view to add a new publication.
	 */
	public void showAddPublication() {
		new PublicationConfigurationView(null, controller);

	}

	/**
	 * shows the Standard error message.
	 * 
	 * @param message
	 *            to be displayed
	 * @param title
	 *            of the frame
	 */
	public void showErrorMessage(final String message, final String title) {
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
	public void showInfoMessage(final String message, final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Loads an exiting database.
	 */
	public void loadDatabase() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileFilter(new FileNameExtensionFilter(
				"Extensible Markup Language (*.xml)", "xml"));
		chooser.showOpenDialog(null);
		controller.loadDatabaseDestination(chooser.getSelectedFile());

	}
	/**
	 * saves the current database.
	 */
	public void saveDatabaseAs() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.showSaveDialog(null);
		controller.saveDatabaseAsDestination(chooser.getSelectedFile());
	}
	/**
	 * shows a new frame with the shortcuts of the main menu.
	 */
	public void showShortcuts() {
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

	@Override
	public void update(final Observable arg0, final Object arg1) {
		remove(authorComponent);
		remove(publicationComponent);
		authorComponent = new DatabaseAuthorTable(controller);
		publicationComponent = new DatabasePublicationTable(controller);
		menu = new DatabaseMenuBar(controller);
		setLayout(new GridLayout(2, 1));
		this.add(authorComponent);
		this.add(publicationComponent);
		addKeyListener(new DataBaseViewKeyListner(controller));
		validate();
	}

	public int getSelectedAuthorRow() {
		return ((DatabaseAuthorTable) authorComponent).getSelectedRow();
	}
	public int getSelectedPublicationRow() {
		return ((DatabasePublicationTable) publicationComponent)
				.getSelectedRow();
	}
	public boolean exitDialog() {
		return JOptionPane.showConfirmDialog(null,
				"Do you really want to exit without saving?", "Exit?",
				JOptionPane.YES_NO_OPTION) == 0;
	}
	public boolean removeDialog() {
		return JOptionPane.showConfirmDialog(null,
				"Do you really want to delete this entry?", "Delete??",
				JOptionPane.YES_NO_OPTION) == 0;
	}

	private final class DatabaseViewWindowListner implements WindowListener {
		private final LiteratureDatabaseController controller;
		private DatabaseViewWindowListner(
				final LiteratureDatabaseController controller) {
			this.controller = controller;
		}
		@Override
		public void windowOpened(final WindowEvent e) {
		}
		@Override
		public void windowIconified(final WindowEvent e) {
		}
		@Override
		public void windowDeiconified(final WindowEvent e) {
		}
		@Override
		public void windowDeactivated(final WindowEvent e) {
		}
		@Override
		public void windowClosing(final WindowEvent e) {
			controller.exit();
		}
		@Override
		public void windowClosed(final WindowEvent e) {
		}
		@Override
		public void windowActivated(final WindowEvent e) {
		}
	}
}
