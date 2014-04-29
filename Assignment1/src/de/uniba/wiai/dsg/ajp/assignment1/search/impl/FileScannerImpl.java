/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.IScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultFound;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultNotFound;

/**
 * Class to search a given file for a given token. *
 * 
 * @author Mathias Mueller
 * 
 */
public class FileScannerImpl implements FileScanner {
    // TODO EXCEPTION HANDLING
    // TODO test
    @Override
    public List<IScanResult> scanFile(final Path path, final String token)
	    throws IOException {
	// input validation:
	// path== null
	// path does not exist
	// path is not a file
	if (path == null) {
	    throw new IllegalArgumentException("path is null");
	}
	if (!Files.exists(path)) {
	    throw new IllegalArgumentException("path does not exist");
	}
	if (!Files.isRegularFile(path)) {
	    throw new IllegalArgumentException("path is not a file.");
	}
	// the result list where the ScanResults are added to
	final List<IScanResult> result = new ArrayList<IScanResult>();
	final String fileName = path.getFileName().toString();
	// iterates through each line and searches each line for the token.
	try (BufferedReader reader = Files.newBufferedReader(path,
		StandardCharsets.UTF_8)) {
	    String line = reader.readLine();

	    int lineCounter = 0;
	    while (line != null) {
		lineCounter++;
		// searches the line for the token and add the results to the
		// result list
		result.addAll(searchString(line, token, fileName, lineCounter));
		// next line
		line = reader.readLine();
	    }
	}
	// when no token is found in the file a ScanResultNotFound is added to
	// the list to indicate that the file has no hits.
	if (result.isEmpty()) {
	    final ScanResultNotFound notFound = new ScanResultNotFound();
	    notFound.fileName = fileName;
	    notFound.token = token;
	    result.add(notFound);
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
    private List<IScanResult> searchString(final String line,
	    final String token, final String fileName, final int lineCounter) {
	// the result list
	final List<IScanResult> result = new ArrayList<IScanResult>();
	// the lenth of the line and the token
	final int lineLength = line.length();
	final int tokenLength = token.length();
	for (int i = 0; i <= lineLength - tokenLength; i++) {
	    // when the substring from i with the tokenlength is the token a
	    // ScanResult is created.
	    final String subString = line.substring(i, i + tokenLength);
	    if (token.equals(subString)) {
		final ScanResultFound tempResult = new ScanResultFound();
		// configuration of the current ScanResult
		tempResult.lineContent = line;
		tempResult.token = token;
		tempResult.column = i;
		tempResult.column = lineCounter;
		tempResult.fileName = fileName;
		result.add(tempResult);
		// the start of the next substring is the index after the
		// substring ended.
		i = i + tokenLength - 1;
	    }
	}
	return result;
    }

}
