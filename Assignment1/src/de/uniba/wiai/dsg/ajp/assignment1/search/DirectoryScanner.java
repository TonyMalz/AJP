/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.nio.file.Path;
import java.util.List;

/**
 * interface to represent a scanner which searches for all files which have a
 * given extention.
 * 
 * @author Mathias
 * 
 */
public interface DirectoryScanner {
    /**
     * iterates through the file system. and returns all paths to files which
     * have the given extension.
     * 
     * @param root
     *            the root path
     * @param fileExtention
     *            the file extension to be filtered
     * @return all paths to files which have the given file extension
     */
    List<Path> scanFileSystem(Path root, String fileExtension)
	    throws TokenFinderException;
}
