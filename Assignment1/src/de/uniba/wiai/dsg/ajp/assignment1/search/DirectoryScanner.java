/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface to represent a scanner which searches for all files which have a
 * given extension.
 * 
 * @author Tony
 * 
 */
public interface DirectoryScanner {
    /**
     * Returns list of file paths within the given root folder. Optionally
     * confines results by a given file extension.
     * 
     * @return the list of matching file paths
     * @throws TokenFinderException
     *             in case an error occurs while trying to traverse the file
     *             tree
     */
    List<Path> getFilePaths() throws TokenFinderException;
}
