/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Mathias
 * 
 */
public interface OutputFormatter {
    // TODO implment Christian

    void show(Path resultPath, List<ScanResult> results);
}
