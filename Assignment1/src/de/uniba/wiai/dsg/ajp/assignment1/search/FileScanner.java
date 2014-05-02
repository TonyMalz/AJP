package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.nio.file.Path;
import java.util.List;

/**
 * scanns
 * 
 * @author Mathias
 * 
 */
public interface FileScanner {
    /**
     * scans the given file of the path for the token. Returns the result as
     * ScanResults.
     * 
     * @param paths
     *            of the files to be searched
     * @param token
     *            to searched for.
     * @return the results of the search
     * @throws TokenFinderException
     *             in case an error occurs while trying to read a file
     * @throws TokenFinderexception
     *             in case an error occurs while trying to find the token
     */
    List<ScanResult> getScanResults(List<Path> paths, String token)
	    throws TokenFinderException;
}
