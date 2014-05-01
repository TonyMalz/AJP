package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.DirectoryScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.FileScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.OutputFormatter;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinder;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

/**
 * 
 */
public class SimpleTokenFinder implements TokenFinder {

    public SimpleTokenFinder() {
	/*
	 * DO NOT REMOVE
	 * 
	 * REQUIRED FOR GRADING
	 */
    }

    @Override
    public void search(final SearchTask task) throws TokenFinderException,
	    IOException {
	/**
	 * TODO Validierung vlt in eigene Methode auslagern?
	 * (zB -> validateSearchTask())
	 * Eigentlich will man den ganzen Schlonz hier ja nicht lesen
	 * Und wenn Validierung hier, braucht mans dann auch noch in den einzelnen
	 * Klassen?
	 * Also an einer Zentralen Stelle macht mE mehr Sinn und führt dann auch
	 *  nicht zu Verwirrungen, wie nämlich, dass die FileExtension hier nicht null
	 *  sein darf, im DirectoryScanner aber schon :/
	 */
	// input validation
	if (task == null) {
	    throw new IllegalArgumentException("task is null");
	}
	final String rootPath = task.getRootFolder();
	if (rootPath == null) {
	    throw new IllegalArgumentException("the root Path is null");
	}
	final String fileExtension = task.getFileExtension();
	if (fileExtension == null) {
	    throw new IllegalArgumentException("file extention is null");
	}
	final String token = task.getToken();
	if (token == null) {
	    throw new IllegalArgumentException("token is null");
	}
	final String outputPath = task.getResultFile();
	if (outputPath == null) {
	    throw new IllegalArgumentException("outputpath is null");
	}
	
	
	/**
	 * TODO Entrümpeln
	 * Brauchts das wirklich; steht doch alles im SearchTask obj?
	 * Bin mittlerweile ja eher dafür, das teil im KOnstruktor einfach 
	 * an die einzelnen Module weiter zu reichen, sprich zB so:
	 * 
	 * List<Path> filePaths = new DirectoryScannerImpl(task).getFilePaths();
	 * List<ScanResult> scanResults = new FileScannerImpl(task).getScanResults();
	 * new OutputFormatterImpl(task).setResults(scanResults).writeToFile();
	 * 
	 * DONE
	 * ok das ganze dann noch in nen try-catch block quetschen...
	 * Und so könnte man sich auch die ganzen Kommentare sparen, da
	 * die 3 Zeilen code realtiv selbst erklärend sind, also jetzt rein subjektiv
	 * aus meiner Sicht ;)
	 * 
	 */
	// getting the input and outputPath
	final Path resultPath = Paths.get(outputPath);
	final Path root = Paths.get(rootPath);
        
	// list where all paths with the given extension are stored
	final DirectoryScanner directoryScanner = new DirectoryScannerImpl();
	final List<Path> pathsWithExtention = directoryScanner.scanFileSystem(
		root, fileExtension);

	// list for all the results where the token was found.
	final List<ScanResult> searchResultUnfiltered = new ArrayList<ScanResult>();
	// when there are no paths with the given extension only one ScanResult
	// is added to the list to indicate that there are no paths.
	if (pathsWithExtention.isEmpty()) {
	    // TODO come up with a solution for this problem and fix it

	} else {
	    // the fileScanner searches every filePath for the token and adds
	    // them to the list.
	    final FileScanner fileScanner = new FileScannerImpl();
	    for (final Path path : pathsWithExtention) {
		searchResultUnfiltered
			.addAll(fileScanner.scanFile(path, token));
	    }
	}

	// the output of the results.
	final OutputFormatter outputFormatter = new OutputFormatterImpl();
	outputFormatter.show(resultPath, searchResultUnfiltered, task);

    }
}
