package de.uniba.wiai.dsg.ajp.assignment1.search;

import java.io.IOException;

/**
 * The interface to be implemented
 */
public interface TokenFinder {

	void search(SearchTask task) throws TokenFinderException, IOException;

}
