package de.uniba.wiai.dsg.ajp.assignment1.search.impl.result;


/**
 * class to represent a ScanResult in which no token was found. The empty result
 * only contains the fileName and the token.
 * 
 * @author Mathias
 * 
 */
public class ScanResultNotFound implements IScanResult {
    /** the name of the file that was searched. */
    public String fileName;
    /** the token that was searched for. */
    public String token;

    /** Default constructor. */
    public ScanResultNotFound() {

    }

}
