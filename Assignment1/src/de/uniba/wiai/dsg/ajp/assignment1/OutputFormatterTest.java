package de.uniba.wiai.dsg.ajp.assignment1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.OutputFormatterImpl;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.IScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultFound;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultNoPath;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultNotFound;

/**
 * Class for testing OutputFormatter
 * 
 * @author Christian
 * 
 */

public class OutputFormatterTest {

    public static void main(String[] args) throws IOException {
	ScanResultFound sr1 = new ScanResultFound();
	sr1.fileName = "data/other.txt";
	sr1.token = "simple";
	sr1.lineContent = "simple";
	sr1.lineNumber = 2;
	sr1.column = 0;

	ScanResultFound sr2 = new ScanResultFound();
	sr2.fileName = "data/test.txt";
	sr2.token = "simple";
	sr2.lineContent = "very simple and extremely simple";
	sr2.lineNumber = 2;
	sr2.column = 5;

	ScanResultFound sr3 = new ScanResultFound();
	sr3.fileName = "data/test.txt";
	sr3.token = "simple";
	sr3.lineContent = "very simple and extremely simple";
	sr3.lineNumber = 2;
	sr3.column = 26;

	ScanResultNotFound srnf1 = new ScanResultNotFound();
	srnf1.fileName = "data/nothing.txt";
	srnf1.token = "simple";

	ScanResultNotFound srnf2 = new ScanResultNotFound();
	srnf2.fileName = "data/not.txt";
	srnf2.token = "simple";

	ScanResultNoPath srnp1 = new ScanResultNoPath();
	srnp1.token = "simple";

	List<IScanResult> results = new ArrayList<IScanResult>();
	// results.add(srnp1);
	results.add(srnf2);
	results.add(sr1);
	results.add(srnf1);
	results.add(sr2);
	results.add(sr3);

	OutputFormatterImpl o = new OutputFormatterImpl();
	o.show(Paths.get("../test.txt"), results);

	System.out.println("program ran successfully");

    }

}
