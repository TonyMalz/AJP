package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

public class ScanResult {

    /** the name of the file where the token was found. */
    public String fileName;

    /** the token which is found. */
    public String token;

    /** the content of the line where the token is found. */
    public String lineContent;

    /** the line number where the token was found. */
    public int lineNumber;

    /** the index where the token starts in the line. */
    public int column;
}
