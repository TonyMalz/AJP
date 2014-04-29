package de.uniba.wiai.dsg.ajp.assignment1.search;

public class ScanResult {

    /** the name of the file where the token was found. */
    public final String fileName;

    /** the token which is found. */
    public final String token;

    /** the content of the line where the token is found. */
    public final String lineContent;

    /** the line number where the token was found. */
    public final int lineNumber;

    /** the index where the token starts in the line. */
    public final int column;

    /**
     * Constructor for a search result where the token was found.
     * 
     * @param fileName
     *            the name of the file that was searched.
     * @param token
     *            the token that was searched for
     * @param lineContent
     *            content of the line where the token was found
     * @param lineNumber
     *            the number of the line where the token was found.
     * @param column
     *            the index of the beginning of the token in the line where it
     *            was found.
     */
    public ScanResult(final String fileName, final String token,
	    final String lineContent, final int lineNumber, final int column) {
	super();
	this.fileName = fileName;
	this.token = token;
	this.lineContent = lineContent;
	this.lineNumber = lineNumber;
	this.column = column;
    }

    /**
     * Constructor for a search result where the token was not found.
     * 
     * @param fileName
     *            the name of the file that was searched.
     * @param token
     *            the token that was searched for
     */
    public ScanResult(final String fileName, final String token) {
	super();
	this.fileName = fileName;
	this.token = token;
	lineContent = null;
	lineNumber = 0;
	column = 0;
    }

    /**
     * @return the fileName
     */
    public final String getFileName() {
	return fileName;
    }

    /**
     * @return the token
     */
    public final String getToken() {
	return token;
    }

    /**
     * @return the lineContent
     */
    public final String getLineContent() {
	return lineContent;
    }

    /**
     * @return the lineNumber
     */
    public final int getLineNumber() {
	return lineNumber;
    }

    /**
     * @return the column
     */
    public final int getColumn() {
	return column;
    }

}
