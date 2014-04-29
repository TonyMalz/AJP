package de.uniba.wiai.dsg.ajp.assignment1.search.impl.result;

/**
 * Scan result when no path with a give file extension was found in the root
 * folder or in sub folders.
 * 
 * @author Mathias
 * 
 */
public class ScanResultNoPath implements IScanResult {

    /** the token that was searched for. */
    public String token;
    /** the file extension that was searched for. */
    public String fileExtention;
}
