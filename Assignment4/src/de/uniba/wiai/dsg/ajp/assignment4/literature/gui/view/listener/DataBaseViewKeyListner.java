package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
/**
 * 
 * @author mathias
 * 
 */
public class DataBaseViewKeyListner implements KeyListener {
	/** controller. */
	LiteratureDatabaseController controller;
	/**
	 * Constructor.
	 * 
	 * @param controller
	 *            for the listener.
	 */
	public DataBaseViewKeyListner(final LiteratureDatabaseController controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A :
				controller.showAddAuthorView();
				break;
			case KeyEvent.VK_P :
				controller.showAddPublication();
				break;
			case KeyEvent.VK_N :
				controller.createDatabase();
				break;
			case KeyEvent.VK_ESCAPE :
				controller.exit();
				break;
			case KeyEvent.VK_S :
				controller.saveDatabaseAs();
				break;
			case KeyEvent.VK_L :
				controller.loadDatabase();
				break;
			case KeyEvent.VK_DELETE :
				controller.removeAuthorOrPublication();

		}

	}

	@Override
	public void keyReleased(final KeyEvent e) {

	}

	@Override
	public void keyTyped(final KeyEvent e) {

	}
	/**
	 * Get the current shortcuts with a help message.
	 * 
	 * @return the help string
	 */
	public static String[] getHelp() {
		final List<String> helpContenets = new ArrayList<>();
		helpContenets.add("A: Add a new Author");
		helpContenets.add("P: Add a new Publication");
		helpContenets.add("N: Create a new Database");
		helpContenets.add("Esc: Exit menu");
		helpContenets.add("S: Save Database as ...");
		helpContenets.add("L: Load Database");
		return helpContenets.toArray(new String[0]);

	}

}