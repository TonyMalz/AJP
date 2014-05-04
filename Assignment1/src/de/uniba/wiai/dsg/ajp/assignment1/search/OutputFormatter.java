/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.util.List;

/**
 * @author Christian
 * 
 */
public interface OutputFormatter {

    /**
     * formats given search results and writes them into specified output-file
     * 
     * @param results
     *            list of search results
     * @throws TokenFinderException
     */
    void showAndWriteToFile(List<ScanResult> results) throws TokenFinderException;

}
