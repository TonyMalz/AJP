package de.uniba.wiai.dsg.ajp.assignment4.literature;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.models.LiteratureDatabaseModel;

public class Main {

	public static void main(String[] args) {
		// TODO startet eure Anwendung ueber diese main-Methode
		new LiteratureDatabaseController(new LiteratureDatabaseModel());

	}

}
