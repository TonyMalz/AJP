package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.IScanResult;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultFound;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.result.ScanResultNotFound;

public class OutputFormatterImpl implements OutputFormatter {

    @Override
    public void show(Path resultPath, List<IScanResult> results)
	    throws IOException {

	// path must not be null
	if (resultPath == null) {
	    throw new IllegalArgumentException("path is null.");
	}
	// if file exists already -> file has to be a file and overwritable
	if (Files.exists(resultPath)) {
	    if (!Files.isRegularFile(resultPath)) {
		throw new IllegalArgumentException("path is not a file.");
	    }
	    if (!Files.isWritable(resultPath)) {
		throw new IllegalArgumentException("file is not overwritable.");
	    }
	}

	try (BufferedWriter writer = Files.newBufferedWriter(resultPath,
		StandardCharsets.UTF_8)) {
	    // got no results
	    if (results.isEmpty()) {
		writer.write("No search results!");
	    }
	    // got results
	    else {
		int counter = 0;
		String currentFile;
		String token;
		if (results.get(0) instanceof ScanResultFound) {
		    token = ((ScanResultFound) results.get(0)).token;
		} else {
		    token = ((ScanResultNotFound) results.get(0)).token;
		}
		int projectFound = 0;

		while (counter < results.size()) {

		    if (results.get(counter) instanceof ScanResultNotFound) {
			writer.write(((ScanResultNotFound) results.get(counter)).fileName
				+ " includes "
				+ ((ScanResultNotFound) results.get(counter)).token
				+ " " + " 0 time(s)");
			writer.newLine();
			writer.newLine();
			counter++;
		    } else {

			// save fileName as currentFile

			currentFile = ((ScanResultFound) results.get(counter)).fileName;
			int fileFound = 0;

			// while fileNames equal currentFile and not all results
			// are
			// processed, write result-line
			// -> means, write all lines for the whole file
			while (counter < results.size()
				&& results.get(counter) instanceof ScanResultFound
				&& ((ScanResultFound) results.get(counter)).fileName
					.equals(currentFile)) {
			    ScanResultFound scanElement = (ScanResultFound) results
				    .get(counter);
			    writer.write(scanElement.fileName + ":");
			    writer.write(scanElement.lineNumber + ",");
			    writer.write(scanElement.column + " > ");
			    writer.write(scanElement.lineContent.substring(0,
				    scanElement.column));
			    writer.write("**" + scanElement.token + "**");
			    writer.write(scanElement.lineContent
				    .substring((scanElement.column)
					    + scanElement.token.length()));
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

	    }

	}
    }
}
