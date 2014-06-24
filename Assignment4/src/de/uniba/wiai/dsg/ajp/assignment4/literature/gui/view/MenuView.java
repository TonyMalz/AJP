package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.MenuController;
/**
 * manu bar with the NEW,LOAD,SAVE AS and EXIT components.
 * 
 * @author tony
 * 
 */
public class MenuView extends JMenuBar {
	/** the controller of the menu bar. */
	MenuController controller;
	/**
	 * Constructor.
	 * 
	 * @param controller
	 *            of the menu bar.
	 */
	public MenuView(final MenuController controller) {
		super();
		this.controller = controller;

		initComponents();
	}
	/**
	 * Initiates the components of the menu bar.
	 */
	private void initComponents() {
		final JMenu menu = new JMenu("File");

		// new
		final JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.newDatabase();

			}
		});
		menu.add(newItem);

		// load
		final JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.loadDatabase();
			}
		});
		menu.add(loadItem);

		// save as
		final JMenuItem saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.saveDatabase();
			}
		});
		menu.add(saveAsItem);

		// exit
		final JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				controller.exit();

			}
		});
		menu.add(exitItem);

		add(menu);
	}
	/**
	 * shows the dialog to load an exiting database.
	 */
	public void showLoadDatabaseDialog() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileFilter(new FileNameExtensionFilter(
				"Extensible Markup Language (*.xml)", "xml"));
		chooser.showOpenDialog(null);

		final File file = chooser.getSelectedFile();
		if (file != null) {
			controller.loadDatabase(file);
		}
	}
	/**
	 * shows the dialog to save the database.
	 */
	public void showSaveDatabaseDialog() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileFilter(new FileNameExtensionFilter(
				"Extensible Markup Language (*.xml)", "xml"));
		chooser.showSaveDialog(null);

		final File file = chooser.getSelectedFile();
		if (file != null) {
			controller.saveDatabase(file);
		}
	}
	/**
	 * shows the standard error message.
	 * 
	 * @param message
	 *            to be displayed.
	 */
	public void showErrorMessage(final String message) {
		JOptionPane.showMessageDialog(null, message, "Sorry",
				JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * shows the exit dialog
	 * 
	 * @return true when YES selected, false otherwise
	 */
	public boolean exitDialog() {
		return JOptionPane.showConfirmDialog(null,
				"Do you really want to exit?", "Close Application",
				JOptionPane.YES_NO_OPTION) == 0;
	}

}
