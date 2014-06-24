/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

/**
 * @author mathias
 * 
 */
public class MainViewWindowListener implements WindowListener {

	/**
	 * 
	 */
	public MainViewWindowListener() {
	}
	@Override
	public void windowClosing(final WindowEvent e) {
		if (exitDialog()) {
			e.getWindow().dispose();
		}

	}

	@Override
	public void windowActivated(final WindowEvent arg0) {
	}

	@Override
	public void windowClosed(final WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(final WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(final WindowEvent arg0) {
	}

	@Override
	public void windowIconified(final WindowEvent arg0) {
	}

	@Override
	public void windowOpened(final WindowEvent arg0) {
	}
	/**
	 * shows the exit dialog
	 * 
	 * @return true when YES selected, false otherwise
	 */
	private boolean exitDialog() {
		return JOptionPane.showConfirmDialog(null,
				"Do you really want to exit?", "Close Window",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}

}
