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
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uniba.wiai.dsg.ajp.assignment1.search.FileScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl;

/**
 * JUnit test for {@link FileScannerImpl}
 * 
 * @author Mathias
 * 
 */
public class FileScannerTest {
    /** the path where the test files are lying. */
    private final static Path testPath = Paths.get("testPath.txt");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	Files.deleteIfExists(testPath);
	Files.createFile(testPath);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	Files.delete(testPath);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	Files.delete(testPath);
	Files.createFile(testPath);
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     */
    @Test
    public final void testScanFileEmptyFile() throws TokenFinderException {
	final FileScanner scanner = new FileScannerImpl();
	final List<ScanResult> results = scanner.scanFile(testPath, "test");
	Assert.assertEquals(results.size(), 1, 0);
	final ScanResult result = results.get(0);
	// no results found
	Assert.assertNull(result.lineContent);
	Assert.assertEquals(result.column, 0, 0);
	Assert.assertEquals(result.lineNumber, 0, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     */
    @Test
    public final void testScanFileNoToken() throws TokenFinderException,
	    IOException {
	try (BufferedWriter writer = Files.newBufferedWriter(testPath,
		StandardCharsets.UTF_8)) {
	    writer.write("scenner will find nothing");
	}
	final FileScanner scanner = new FileScannerImpl();
	final List<ScanResult> results = scanner.scanFile(testPath, "test");
	Assert.assertEquals(results.size(), 1, 0);
	final ScanResult result = results.get(0);
	// no results found
	Assert.assertNull(result.lineContent);
	Assert.assertEquals(result.column, 0, 0);
	Assert.assertEquals(result.lineNumber, 0, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     * @throws IOException
     */
    @Test
    public final void testScanFileOneToken() throws TokenFinderException,
	    IOException {
	try (BufferedWriter writer = Files.newBufferedWriter(testPath,
		StandardCharsets.UTF_8)) {
	    writer.write("test");
	}
	final FileScanner scanner = new FileScannerImpl();
	final List<ScanResult> results = scanner.scanFile(testPath, "test");
	Assert.assertEquals(results.size(), 1, 0);
	final ScanResult result = results.get(0);
	// no results found
	Assert.assertTrue(result.lineContent.equals("test"));
	Assert.assertEquals(result.column, 0, 0);
	Assert.assertEquals(result.lineNumber, 1, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));
    }

    /**
     * Test method for
     * {@link de.uniba.wiai.dsg.ajp.assignment1.search.impl.FileScannerImpl#scanFile(java.nio.file.Path, java.lang.String)}
     * .
     * 
     * @throws TokenFinderException
     * @throws IOException
     */
    @Test
    public final void testScanFileMultipleToken() throws TokenFinderException,
	    IOException {
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
	}
	final FileScanner scanner = new FileScannerImpl();
	final List<ScanResult> results = scanner.scanFile(testPath, "test");
	Assert.assertEquals(results.size(), 4, 0);
	ScanResult result = results.get(0);

	Assert.assertTrue(result.lineContent.equals(lines.get(0)));
	Assert.assertEquals(result.column, 0, 0);
	Assert.assertEquals(result.lineNumber, 1, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));

	result = results.get(1);

	Assert.assertTrue(result.lineContent.equals(lines.get(0)));
	Assert.assertEquals(result.column, 5, 0);
	Assert.assertEquals(result.lineNumber, 1, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));

	result = results.get(2);

	Assert.assertTrue(result.lineContent.equals(lines.get(2)));
	Assert.assertEquals(result.column, 0, 0);
	Assert.assertEquals(result.lineNumber, 3, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));

	result = results.get(3);

	Assert.assertTrue(result.lineContent.equals(lines.get(2)));
	Assert.assertEquals(result.column, 4, 0);
	Assert.assertEquals(result.lineNumber, 3, 0);
	Assert.assertEquals(result.getFileName(), testPath);
	Assert.assertTrue(result.getToken().equals("test"));
    }
}
