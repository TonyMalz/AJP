/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.FileScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

/**
 * Class to search a given file for a given token. *
 * 
 * @author Mathias Mueller
 * 
 */
public class FileScannerImpl implements FileScanner {
    /** the search task for the search. */
    private final SearchTask task;

    /**
     * Constructor.
     * 
     * @param task
     *            the task to searched for.
     */
    public FileScannerImpl(final SearchTask task) {
	if (task == null) {
	    throw new IllegalArgumentException("Search task is null.");
	}
	this.task = task;
    }

    @Override
    public List<ScanResult> getScanResults(final List<Path> paths)
	    throws TokenFinderException {
	// input validation:
	if (paths == null) {
	    throw new IllegalArgumentException("Paths is null.");
	}
	if (task.getToken() == null) {
	    throw new IllegalArgumentException("Token is null.");
	}
	// for each path it is tested if the path exists and if it is a file.
	for (final Path path : paths) {
	    if (path == null) {
		throw new IllegalArgumentException("path is null");
	    }
	    try {
		if (Files.notExists(path)) {
		    throw new IllegalArgumentException("path does not exist");
		}
		if (!Files.isRegularFile(path)) {
		    throw new IllegalArgumentException("path is not a file.");
		}
	    } catch (final SecurityException e) {
		throw new TokenFinderException("Access denied to the path: "
			+ path.toString(), e);
	    }
	}

	final List<ScanResult> result = new ArrayList<ScanResult>();
	for (final Path path : paths) {
	    result.addAll(scanFile(path));
	}
	return result;
    }

    /**
     * searches through a particular path which must be a file for a specific
     * token. returns a list of the results.
     * 
     * @param path
     *            to be searched
     * @return the resulting list
     * @throws TokenFinderException
     *             in case an error occurs while trying to access the file
     */
    private List<ScanResult> scanFile(final Path path)
	    throws TokenFinderException {
	// the result list where the ScanResults are added to
	final List<ScanResult> result = new ArrayList<ScanResult>();

	// iterates through each line and searches each line for the token.
	try (BufferedReader reader = Files.newBufferedReader(path,
		StandardCharsets.UTF_8)) {
	    String line = reader.readLine();

	    int lineCounter = 0;
	    while (line != null) {
		lineCounter++;
		// searches the line for the token and add the results to the
		// result list
		result.addAll(searchString(line, task.getToken(), path,
			lineCounter));
		// next line
		line = reader.readLine();
	    }
	    
	    // if no token was found in the file an empty ScanResult is added to
	    // the list to indicate that the file has no hits.
	    if (result.isEmpty()) {
		final ScanResult notFound = new ScanResult(path, task.getToken());

		result.add(notFound);
	    }
	} 
	catch(MalformedInputException e){
	    //ignore unsupported charsets
	}
	catch (final IOException e) {
	    throw new TokenFinderException("an I/O error occured.", e);
	} catch (final SecurityException e) {
	    throw new TokenFinderException("Access denied to the path: "
		    + path.toString(), e);
	}
	
	return result;
    }

    /**
     * searches the given line for the token.
     * 
     * @param line
     *            to be searched.
     * @param token
     *            to searched for.
     * @param fileName
     *            where the line occurs.
     * @param lineCounter
     *            the lineNumber of the line
     * @return
     */
    private List<ScanResult> searchString(final String line,
	    final String token, final Path file, final int lineCounter) {
	// the result list
	final List<ScanResult> result = new ArrayList<ScanResult>();
	// the length of the line and the token
	final int lineLength = line.length();
	final int tokenLength = token.length();
	for (int i = 0; i <= lineLength - tokenLength; i++) {
	    // when the substring from i with the tokenlength is the token a
	    // ScanResult is created.
	    final String subString = line.substring(i, i + tokenLength);
	    if (token.equals(subString)) {
		// configuration of the current ScanResult
		final ScanResult tempResult = new ScanResult(file, token, line,
			lineCounter, i);

		result.add(tempResult);
		// the start of the next substring is the index after the
		// substring ended.
		i = i + tokenLength - 1;
	    }
	}
	return result;
    }

}
