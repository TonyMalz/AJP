package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

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
     * @throws IOException
     *             in case an error occurs
     */
    List<IScanResult> scanFile(Path path, String token) throws IOException;
}
