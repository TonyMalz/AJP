/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Mathias
 * 
 */
public interface OutputFormatter {

    /**
     * writes all search results in specified output-file
     * 
     * @param resultPath
     *            path for output-file
     * @param results
     *            list of search results
     * @param task
     * @throws TokenFinderException
     */
    void show(Path resultPath, List<ScanResult> results, SearchTask task)
	    throws TokenFinderException;

}
