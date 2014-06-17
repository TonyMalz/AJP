/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.LiteratureDatabaseView;

/**
 * Menu bar of the main menu. has the items: NEW, LOAD, SAVE AS, EXIT, and
 * SHORTCUTS.
 * 
 * @author mathias
 * 
 */
public class DatabaseMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor generates a new Menu bar with an option "File". Sub options
	 * are "New", "Load", "Save As","Exit" and "Shortcuts".
	 * */
	public DatabaseMenuBar() {
		final JMenu menu = new JMenu("File");
		final JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LiteratureDatabaseView.createDatabase();

			}
		});
		menu.add(newItem);
		// load
		final JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LiteratureDatabaseView.loadDatabase();

			}
		});
		menu.add(loadItem);
		// save as
		final JMenuItem saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LiteratureDatabaseView.saveDatabaseAs();

			}
		});
		menu.add(saveAsItem);
		// exit
		final JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LiteratureDatabaseView.exit();

			}
		});
		menu.add(exitItem);
		final JMenuItem shortcutItem = new JMenuItem("Shortcuts");
		shortcutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LiteratureDatabaseView.showShortcuts();

			}
		});
		menu.add(shortcutItem);
		add(menu);
	}

}
