/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.IScanResult;

/**
 * @author Mathias
 * 
 */
public interface OutputFormatter {
    // TODO implment Christian

    /**
     * writes all search results in specified output-file
     * 
     * @param resultPath
     *            path for output-file
     * @param results
     *            list of search results
     * @throws IOException
     */
    void show(Path resultPath, List<IScanResult> results) throws IOException;
}
