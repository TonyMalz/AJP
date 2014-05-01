package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.nio.file.Path;


/**
 * TODO implizite Logik in Methode auslagern
 * relativ unschön, dass man die Kommentare der Felder lesen muss, 
 * um zu verstehen, wann das Objekt denn den Token nicht gefunde hat
 * 
 * Das ist zu viel Magie mE
 * 
 * besser ne Methode mit sprechenden Namen wie zb
 * tokenNotFound(), hasNoToken() oder isEmpty(),etc
 * die man dann einfach aufruft und nen boolean zurückgibt,
 * wenn entsprehcend auch nix gefunden wurde, ohne dass der Aufrufer
 * die internen Details kennen muss
 * 
  */
public class ScanResult {

    /** the name of the file where the token was found. */
    public final Path fileName;

    /** the token which is found. */
    public final String token;

    /**
     * the content of the line where the token is found. Is null when there was
     * no token found in the file.
     */
    public final String lineContent;

    /**
     * the line number where the token was found. Is 0 when there was no token
     * found in the file.
     */
    public final int lineNumber;

    /**
     * the index where the token starts in the line. Is 0 when there was no
     * token found in the file.
     */
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
    public ScanResult(final Path fileName, final String token,
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
    public ScanResult(final Path fileName, final String token) {
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
    public final Path getFileName() {
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
