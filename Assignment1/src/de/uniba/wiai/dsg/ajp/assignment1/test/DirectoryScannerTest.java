package de.uniba.wiai.dsg.ajp.assignment1.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.DirectoryScannerImpl;

public class DirectoryScannerTest {

    public static void main(String[] args) {
	boolean error = true;
	boolean glError = false;
	for (Path file : new DirectoryScannerImpl().scanFileSystem(
		Paths.get(""), "")) {
	    if (file.getFileName().toString()
		    .equals("DirectoryScannerTest.java")) {
		error = false;
	    }
	}
	if (error) {
	    glError = true;
	    System.out
		    .println("Error: file not found: DirectoryScannerTest.java");
	}

	error = false;
	for (Path file : new DirectoryScannerImpl().scanFileSystem(
		Paths.get("../data"), ".txt")) {
	    if (file.getFileName().toString().equals("muh.xml")) {
		error = true;
	    }
	}
	if (error) {
	    glError = true;
	    System.out.println("Error: found file: muh.xml");
	}

	if (!glError) {
	    System.out.println("Apperently there ain't no errors, yippie :)");
	}
    }

}
