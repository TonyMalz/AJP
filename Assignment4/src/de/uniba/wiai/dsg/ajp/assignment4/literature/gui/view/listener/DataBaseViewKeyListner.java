package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.LiteratureDatabaseView;
/**
 * 
 * @author mathias
 * 
 */
public class DataBaseViewKeyListner implements KeyListener {

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A :
				LiteratureDatabaseView.showAddAuthorView();
				break;
			case KeyEvent.VK_P :
				LiteratureDatabaseView.showAddPublication();
				break;
			case KeyEvent.VK_N :
				LiteratureDatabaseView.createDatabase();
				break;
			case KeyEvent.VK_ESCAPE :
				LiteratureDatabaseView.exit();
				break;
			case KeyEvent.VK_S :
				LiteratureDatabaseView.saveDatabaseAs();
				break;
			case KeyEvent.VK_L :
				LiteratureDatabaseView.loadDatabase();
				break;

		}

	}

	@Override
	public void keyReleased(final KeyEvent e) {

	}

	@Override
	public void keyTyped(final KeyEvent e) {

	}

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