/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment1.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.FileScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl;

/**
 * 
 * @author Mathias
 * 
 */
public class FileScannerTest {

    private static boolean testSuccessfull;
    /** the path where the test files are lying. */
    private static final Path testPath = Paths.get("testPath.txt");

    private static final List<Path> testPathList = Arrays
	    .asList(new Path[] { testPath });

    public static void main(final String[] args) throws IOException,
	    TokenFinderException {
	setUpBeforeClass();
	setUp();
	testScanFileEmptyFile();
	if (!testSuccessfull) {
	    System.out.println("TEST WITH EMPTY FILE WAS NOT SUCCEESSFULL");
	} else {
	    System.out.println("TEST WITH EMPTY FILE WAS SUCCEESSFULL.");
	}
	setUp();
	testScanFileNoToken();
	if (!testSuccessfull) {
	    System.out
		    .println("TEST WITH FILE WITH NO TOKEN WAS NOT SUCCEESSFULL");
	} else {
	    System.out.println("TEST WITH FILE WITH NO TOKEN WAS SUCCEESSFULL");
	}
	setUp();
	testScanFileOneToken();
	if (!testSuccessfull) {
	    System.out
		    .println("TEST WITH FILE WITH ONE TOKEN WAS NOT SUCCEESSFULL");
	} else {
	    System.out
		    .println("TEST WITH FILE WITH ONE TOKEN WAS SUCCEESSFULL");
	}
	setUp();
	testScanFileMultipleToken();
	;
	if (!testSuccessfull) {
	    System.out
		    .println("TEST WITH FILE WITH MULTIPLE TOKEN WAS NOT SUCCEESSFULL");
	} else {
	    System.out
		    .println("TEST WITH FILE WITH MULTIPLE TOKEN WAS SUCCEESSFULL");
	}

    }

    public static void setUpBeforeClass() throws IOException {
	Files.deleteIfExists(testPath);
	Files.createFile(testPath);
    }

    /**
     * @throws IOException
     * @throws java.lang.Exception
     */
    public static void tearDownAfterClass() throws IOException {
	Files.delete(testPath);
    }

    /**
     * @throws IOException
     * @throws java.lang.Exception
     */
    public static void setUp() throws IOException {
	Files.delete(testPath);
	Files.createFile(testPath);
	testSuccessfull = true;
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     */

    public static final void testScanFileEmptyFile()
	    throws TokenFinderException {
	final SearchTask task = new SearchTask();
	task.setToken("test");
	final FileScanner scanner = new FileScannerImpl(task);
	final List<ScanResult> results = scanner.getScanResults(testPathList);
	assertEquals(results.size(), 1);
	final ScanResult result = results.get(0);
	// no results found
	assertNull(result.lineContent);
	assertEquals(result.column, 0);
	assertEquals(result.lineNumber, 0);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     */

    public static final void testScanFileNoToken() throws TokenFinderException,
	    IOException {
	try (BufferedWriter writer = Files.newBufferedWriter(testPath,
		StandardCharsets.UTF_8)) {
	    writer.write("scenner will find nothing");
	}
	final SearchTask task = new SearchTask();
	task.setToken("test");
	final FileScanner scanner = new FileScannerImpl(task);
	final List<ScanResult> results = scanner.getScanResults(testPathList);
	assertEquals(results.size(), 1);
	final ScanResult result = results.get(0);
	// no results found
	assertNull(result.lineContent);
	assertEquals(result.column, 0);
	assertEquals(result.lineNumber, 0);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     * @throws IOException
     */

    public static final void testScanFileOneToken()
	    throws TokenFinderException, IOException {
	try (BufferedWriter writer = Files.newBufferedWriter(testPath,
		StandardCharsets.UTF_8)) {
	    writer.write("test");
	}
	final SearchTask task = new SearchTask();
	task.setToken("test");
	final FileScanner scanner = new FileScannerImpl(task);
	final List<ScanResult> results = scanner.getScanResults(testPathList);
	assertEquals(results.size(), 1);
	final ScanResult result = results.get(0);
	// no results found
	assertTrue(result.lineContent.equals("test"));
	assertEquals(result.column, 0);
	assertEquals(result.lineNumber, 1);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     * @throws IOException
     */

    public static final void testScanFileMultipleToken()
	    throws TokenFinderException, IOException {
	final List<String> lines = new ArrayList<String>();
	try (BufferedWriter writer = Files.newBufferedWriter(testPath,
		StandardCharsets.UTF_8)) {
	    lines.add("test test");
	    writer.write("test test");
	    writer.newLine();
	    lines.add("no token");
	    writer.write("no token");
	    writer.newLine();
	    lines.add("testtest");
	    writer.write("testtest");

	    writer.newLine();
	    lines.add("for test: this is no real testing!");
	    writer.write("for test: this is no real testing!");

	}
	final SearchTask task = new SearchTask();
	task.setToken("test");
	final FileScanner scanner = new FileScannerImpl(task);
	final List<ScanResult> results = scanner.getScanResults(testPathList);
	assertEquals(results.size(), 6);
	ScanResult result = results.get(0);

	assertTrue(result.lineContent.equals(lines.get(0)));
	assertEquals(result.column, 0);
	assertEquals(result.lineNumber, 1);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));

	result = results.get(1);

	assertTrue(result.lineContent.equals(lines.get(0)));
	assertEquals(result.column, 5);
	assertEquals(result.lineNumber, 1);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));

	result = results.get(2);

	assertTrue(result.lineContent.equals(lines.get(2)));
	assertEquals(result.column, 0);
	assertEquals(result.lineNumber, 3);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));

	result = results.get(3);

	assertTrue(result.lineContent.equals(lines.get(2)));
	assertEquals(result.column, 4);
	assertEquals(result.lineNumber, 3);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));

	result = results.get(4);

	assertTrue(result.lineContent.equals(lines.get(3)));
	assertEquals(result.column, 4);
	assertEquals(result.lineNumber, 4);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));

	result = results.get(5);

	assertTrue(result.lineContent.equals(lines.get(3)));
	assertEquals(result.column, 26);
	assertEquals(result.lineNumber, 4);
	assertEquals(result.getFileName(), testPath);
	assertTrue(result.getToken().equals("test"));
    }

    private static void assertEquals(final int a, final int b) {
	if (a != b) {
	    System.out.println("Assertion failed! Expected: " + a
		    + ", Actual: " + b);
	    testSuccessfull = false;
	}

    }

    private static void assertTrue(final boolean bool) {
	if (!bool) {
	    System.out.println("Asserrtion failed! Was false.");
	    testSuccessfull = false;
	}

    }

    private static void assertNull(final String string) {
	if (string != null) {
	    System.out.println(" Assertion failed: '" + string
		    + "' was not null.");
	    testSuccessfull = false;
	}
    }

    private static void assertEquals(final Object obj1, final Object obj2) {
	if (obj1 != obj2) {
	    System.out.println("Assertion failed! Expected: " + obj1
		    + ", Actual: " + obj2);
	    testSuccessfull = false;
	}

    }
}
