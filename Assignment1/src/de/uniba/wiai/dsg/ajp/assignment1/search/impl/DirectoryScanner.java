/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

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
    // TODO implement Tony
    /**
     * iterates through the file system. and returns all paths to files which
     * have the given extention.
     * 
     * @param root
     *            the root path
     * @param fileExtention
     *            the file extention to be filtered
     * @return all paths to files which have the given file extention
     */
    List<Path> scanFileSystem(Path root, String fileExtention);
}