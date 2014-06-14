/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
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
	 * are "New", "Load", "Save As" and "Exit".
	 * */
	public DatabaseMenuBar() {
		final JMenu menu = new JMenu("File");
		final JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO connect to controller when ew menuItem is pressed

			}
		});
		menu.add(newItem);
		// load
		final JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO connect to controller when Load menuItem is pressed

			}
		});
		menu.add(loadItem);
		// save as
		final JMenuItem saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO connect to controller when "Save As" menuItem is pressed

			}
		});
		menu.add(saveAsItem);
		// exit
		final JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO connect to controller when exit menuItem is pressed

			}
		});
		menu.add(exitItem);
		add(menu);
	}

}
