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
     * Iterates through the file system. and returns all paths to files which
     * have the given extension.
     * 
     * @return all paths to files which have the given file extension
     */
    List<Path> getFilePaths() throws TokenFinderException;
}
