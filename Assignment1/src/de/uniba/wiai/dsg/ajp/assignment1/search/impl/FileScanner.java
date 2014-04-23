package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

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

    List<ScanResult> scanFile(Path path, String token);
}
