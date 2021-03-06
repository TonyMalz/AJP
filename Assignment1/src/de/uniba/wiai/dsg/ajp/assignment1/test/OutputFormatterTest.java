package de.uniba.wiai.dsg.ajp.assignment1.test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.OutputFormatter;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.OutputFormatterImpl;

/**
 * Class for testing OutputFormatter
 * 
 * @author Christian
 * 
 */

public class OutputFormatterTest {

    public static void main(String[] args) throws IOException,
	    TokenFinderException {
	ScanResult sr1 = new ScanResult(Paths.get("data/other.txt"), "simple",
		"simple", 2, 0);

	ScanResult sr2 = new ScanResult(Paths.get("data/test.txt"), "simple",
		"very simple and extremely simple", 2, 5);

	ScanResult sr3 = new ScanResult(Paths.get("data/test.txt"), "simple",
		"very simple and extremely simple", 2, 26);

	ScanResult sr4 = new ScanResult(Paths.get("data/nothing.txt"), "simple");

	ScanResult sr5 = new ScanResult(Paths.get("data/not.txt"), "simple");

	List<ScanResult> results = new ArrayList<ScanResult>();
	results.add(sr5);
	results.add(sr1);
	results.add(sr4);
	results.add(sr2);
	results.add(sr3);

	SearchTask task = new SearchTask();
	task.setToken("simple");
	task.setResultFile("../test.txt");
	try {
	    OutputFormatter formatter = new OutputFormatterImpl(task);
	    formatter.showAndWriteToFile(results);
	    System.out.println("program ran successfully");

	} catch (TokenFinderException e) {
	    System.out.println("Ok, caught token finder exception");
	} catch (Exception e) {
	    System.out.println("uupsie ");
	}

    }

}
