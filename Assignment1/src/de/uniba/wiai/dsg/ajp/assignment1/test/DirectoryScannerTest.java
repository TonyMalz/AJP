package de.uniba.wiai.dsg.ajp.assignment1.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.DirectoryScannerImpl;

public class DirectoryScannerTest {

    public static void main(String[] args) {
	for (Path file : new DirectoryScannerImpl().scanFileSystem(
		Paths.get(""), ".java")) {
	    System.out.println(file);
	}

	for (Path file : new DirectoryScannerImpl().scanFileSystem(
		Paths.get("../data"), ".txt")) {
	    System.out.println(file);
	}
    }

}
