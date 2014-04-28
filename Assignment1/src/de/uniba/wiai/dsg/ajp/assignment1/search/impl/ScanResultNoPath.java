package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

/**
 * Scan result when no path with a give file extention was found in the root
 * folder or in ub folders.
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
