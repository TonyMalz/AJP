package de.uniba.wiai.dsg.ajp.assignment1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.OutputFormatterImpl;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.ScanResult;

/**
 * Class for testing OutputFormatter
 * 
 * @author Christian
 * 
 */

public class OutputFormatterTest {

    public static void main(String[] args) throws IOException {
	ScanResult sr1 = new ScanResult();
	sr1.fileName = "data/other.txt";
	sr1.token = "simple";
	sr1.lineContent = "simple";
	sr1.lineNumber = 2;
	sr1.column = 0;

	ScanResult sr2 = new ScanResult();
	sr2.fileName = "data/test.txt";
	sr2.token = "simple";
	sr2.lineContent = "very simple and extremely simple";
	sr2.lineNumber = 2;
	sr2.column = 5;

	ScanResult sr3 = new ScanResult();
	sr3.fileName = "data/test.txt";
	sr3.token = "simple";
	sr3.lineContent = "very simple and extremely simple";
	sr3.lineNumber = 2;
	sr3.column = 26;

	List<ScanResult> results = new ArrayList<ScanResult>();
	results.add(sr1);
	results.add(sr2);
	results.add(sr3);

	OutputFormatterImpl o = new OutputFormatterImpl();
	o.show(Paths.get("../test.txt"), results);

	System.out.println("program ran successfully");

    }

}
