package de.uniba.wiai.dsg.ajp.assignment1.test;

import java.nio.file.Path;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.DirectoryScannerImpl;

/**
 * poor man's test ;)
 * 
 * @author Tony
 * 
 */
public class DirectoryScannerTest {

    public static void main(String[] args) {

	boolean error = true;
	boolean glError = false;

	try {
	    for (Path file : getScannerPaths("", "")) {
		if (file.getFileName().toString()
			.equals("DirectoryScannerTest.java")) {
		    error = false;
		}
	    }
	} catch (TokenFinderException e) {
	    System.out.println("Error: " + e.getMessage());
	    glError = true;
	}
	if (error) {
	    glError = true;
	    System.out
		    .println("Error: file not found: DirectoryScannerTest.java");
	}

	error = false;
	try {
	    for (Path file : getScannerPaths("../data", ".txt")) {
		if (file.getFileName().toString().equals("muh.xml")) {
		    error = true;
		}
	    }
	} catch (TokenFinderException e) {
	    System.out.println("Error: " + e.getMessage());
	    glError = true;
	}
	if (error) {
	    glError = true;
	    System.out.println("Error: found file: muh.xml");
	}

	error = true;
	try {
	    for (Path file : getScannerPaths("../data", ".txt")) {
		if (file.getFileName().toString().equals("other.txt")) {
		    error = false;
		}
	    }
	} catch (TokenFinderException e) {
	    System.out.println("Error: " + e.getMessage());
	    glError = true;
	}
	if (error) {
	    glError = true;
	    System.out.println("Error: file not found: other.txt");
	}

	if (!glError) {
	    System.out.println("Apperently there ain't no errors, yippie :)");
	}
    }

    private static List<Path> getScannerPaths(String rootFolder,
	    String fileExtension) throws TokenFinderException {
	SearchTask task = new SearchTask();
	task.setFileExtension(fileExtension);
	task.setRootFolder(rootFolder);

	return new DirectoryScannerImpl(task).getFilePaths();
    }

}
