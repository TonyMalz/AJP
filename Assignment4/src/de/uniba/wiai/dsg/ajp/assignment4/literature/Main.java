package de.uniba.wiai.dsg.ajp.assignment4.literature;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.MainController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.MainModel;
/**
 * Main starter class.
 */
public class Main {

	public static void main(final String[] args) {
		new MainController(new MainModel());
	}
}
