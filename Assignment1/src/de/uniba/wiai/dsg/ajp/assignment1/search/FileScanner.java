package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * scanns
 * 
 * @author Mathias
 * 
 */
public interface FileScanner {
    // TODO implement Mathias
    /**
     * scans the given file of the path for the token. Returns the result as
     * ScanResults.
     * 
     * @param path
     *            of the file
     * @param token
     *            to searched for.
     * @return the results of the search
     * @throws TokenFinderException
     *             in case an error occurs while trying to read a file
     * @throws IOException
     *             in case an error occurs
     */
    List<ScanResult> scanFile(Path path, String token)
	    throws TokenFinderException;
}
