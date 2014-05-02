package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void search(final SearchTask task) throws TokenFinderException {

	// input validation
	validateSearchTask(task);

	final String rootPath = task.getRootFolder();
	final String outputPath = task.getResultFile();
	final String token = task.getToken();
	final String fileExtension = task.getFileExtension();

	/**
	 * TODO Entrümpeln Brauchts das wirklich; steht doch alles im SearchTask
	 * obj? Bin mittlerweile ja eher dafür, das teil im KOnstruktor einfach
	 * an die einzelnen Module weiter zu reichen
	 */
	final Path resultPath = Paths.get(outputPath);
	final Path root = Paths.get(rootPath);
	// TODO change constructor for task
	final DirectoryScanner directoryScanner = new DirectoryScannerImpl();
	final List<Path> pathsWithExtention = directoryScanner.scanFileSystem(
		root, fileExtension);

	final FileScanner fileScanner = new FileScannerImpl(task);
	final List<ScanResult> searchResultUnfiltered = fileScanner
		.getScanResults(pathsWithExtention, token);
	// TODO change onstructor for task
	final OutputFormatter outputFormatter = new OutputFormatterImpl();
	outputFormatter.show(resultPath, searchResultUnfiltered, task);

    }

    /**
     * validates the given search task if anything is null, in case any any of
     * the parameters in the task is null an IllegalArgumentException is thrown
     * 
     * @param task
     *            to be validated
     */
    private void validateSearchTask(final SearchTask task) {
	if (task == null) {
	    throw new IllegalArgumentException("task is null");
	}
	if (task.getRootFolder() == null) {
	    throw new IllegalArgumentException("the root Path is null");
	}

	if (task.getToken() == null) {
	    throw new IllegalArgumentException("token is null");
	}
	if (task.getResultFile() == null) {
	    throw new IllegalArgumentException("result File is null");
	}
    }
}
