package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.nio.file.Path;
import java.util.List;

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

	final List<Path> filePaths = new DirectoryScannerImpl(task)
		.getFilePaths();

	final List<ScanResult> scanResults = new FileScannerImpl(task)
		.getScanResults(filePaths);

	new OutputFormatterImpl(task).showAndWriteToFile(scanResults);

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
