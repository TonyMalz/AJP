package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import de.uniba.wiai.dsg.ajp.assignment1.search.OutputFormatter;
import de.uniba.wiai.dsg.ajp.assignment1.search.ScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

public class OutputFormatterImpl implements OutputFormatter {

    private final SearchTask task;

    /**
     * Constructor.
     * 
     * @param task
     *            the task to be printed to console and file.
     */
    public OutputFormatterImpl(final SearchTask task) {
	Objects.requireNonNull(task, "task is null");
	this.task = task;
    }

    @Override
    public void showAndWriteToFile(final List<ScanResult> scanResults)
	    throws TokenFinderException {

	Objects.requireNonNull(scanResults, "scanResults is null");
	Objects.requireNonNull(task.getResultFile(), "result file is null");

	final Path resultPath = Paths.get(task.getResultFile());

	// if file exists already -> file has to be a file and overwritable
	try {
	    if (Files.exists(resultPath)) {
		if (!Files.isRegularFile(resultPath)) {
		    throw new IllegalArgumentException(
			    "result path is not a file");
		}
		if (!Files.isWritable(resultPath)) {
		    throw new IllegalArgumentException(
			    "result file is not overwritable");
		}
	    }
	} catch (final SecurityException e) {
	    throw new TokenFinderException("access denied to the path:"
		    + resultPath.toString(), e);
	}

	try (BufferedWriter writer = getWriter(resultPath)) {

	    final String token = task.getToken();

	    if (scanResults.isEmpty()) {
		// no file was found
		writer.write("The project includes " + token + " 0 time(s)");
	    } else {
		// counter for iterating list "results"
		// projectFound for counting results
		int counter = 0;
		int projectFound = 0;
		Path currentFile;

		while (counter < scanResults.size()) {
		    // if ScanResult isEmpty (has no result),
		    // print project summary
		    if (scanResults.get(counter).isEmpty()) {
			writer.write(scanResults.get(counter).fileName
				+ " includes " + token + " " + " 0 time(s)");
			writer.newLine();
			writer.newLine();
			counter++;
		    } else {

			// save fileName as currentFile
			currentFile = scanResults.get(counter).fileName;
			int fileFound = 0;

			// while not all results are processed and entry is a
			// real found result and fileNames equal currentFile,
			// write result-line
			// -> means, write all lines for the whole file
			while (counter < scanResults.size()

				&& scanResults.get(counter).fileName
					.equals(currentFile)) {
			    final ScanResult scanElement = scanResults
				    .get(counter);
			    writer.write(scanElement.fileName + ":");
			    writer.write(scanElement.lineNumber + ",");
			    writer.write(scanElement.column + " > ");
			    writer.write(scanElement.lineContent.substring(0,
				    scanElement.column));
			    writer.write("**" + token + "**");
			    writer.write(scanElement.lineContent
				    .substring(scanElement.column
					    + token.length()));
			    writer.newLine();
			    counter++;
			    fileFound++;
			}
			// file finished -> file summary
			writer.write(currentFile + " includes " + token + " "
				+ fileFound + " time(s)");
			writer.newLine();
			writer.newLine();
			projectFound = projectFound + fileFound;
		    }

		}

		// project finished -> project summary
		writer.write("The project includes " + token + " "
			+ projectFound + " time(s)");
		writer.newLine();
	    }
	} catch (final IOException e) {
	    throw new TokenFinderException(e);
	} catch (final SecurityException e) {
	    throw new TokenFinderException("access denied to the path:"
		    + resultPath.toString(), e);
	}

    }

    /**
     * @param resultPath
     *            path to output file
     * 
     * @return custom writer to both, System.out and file system
     */
    private BufferedWriter getWriter(final Path resultPath) throws IOException,
	    SecurityException {

	return new FileAndSystemOutWriter(new OutputStreamWriter(
		Files.newOutputStream(resultPath), StandardCharsets.UTF_8));
    }

    /**
     * Custom BufferedWriter object, which outputs to both, System.out and file
     * system
     */
    private class FileAndSystemOutWriter extends BufferedWriter {

	public FileAndSystemOutWriter(final Writer out) {
	    super(out);
	}

	@Override
	public void write(final String str) throws IOException {
	    System.out.print(str);
	    super.write(str);
	}
    }

}
