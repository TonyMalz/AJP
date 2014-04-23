package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinder;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

/**
 * TODO: implement this
 */
public class SimpleTokenFinder implements TokenFinder {

    public SimpleTokenFinder() {
	/*
	 * DO NOT REMOVE
	 * 
	 * REQUIRED FOR GRADING
	 */
    }

    @Override
    public void search(final SearchTask task) throws TokenFinderException,
	    IOException {
	/*
	 * IMPLEMENT THIS
	 */

	final Charset c = StandardCharsets.UTF_8;
	int foundProject = 0;
	final String token = task.getToken();
	final String rootFolder = task.getRootFolder();
	final String fileExtension = task.getFileExtension();
	final BufferedWriter writer = Files.newBufferedWriter(
	        Paths.get(task.getResultFile()), c);
	BufferedReader reader;

	// files and folders of "rootFolder" into fileArray
	final File f = new File(rootFolder);
	String[] fileArray = f.list();
	final List<Path> fileList = new ArrayList<>();

	fileList.addAll(fileList);

	// until "fileList" is empty
	while (!fileList.isEmpty()) {

	    final File tmpFile = fileList.get(0).toFile();

	    // if "tmpFile" is file -> read
	    if (tmpFile.isFile()
		    && fileList.get(0).getFileName().toString()
		            .contains("." + fileExtension)) {

		// read with bufferedReader
		reader = Files.newBufferedReader(fileList.get(0), c);
		String line = reader.readLine();
		int rowCounter = 1;
		int foundFile = 0;
		while (line != null) {

		    // search
		    String searchLine = line;
		    int deletedChars = 0;
		    int tokenIndex;

		    // print and write results
		    while (searchLine.contains(token)) {
			System.out.print(fileList.get(0) + ":");
			writer.write(fileList.get(0) + ":");
			System.out.print(rowCounter);
			writer.write(Integer.toString(rowCounter));
			System.out.print(",");
			writer.write(",");
			// token Index means index of found token in "line" NOT
			// "searchLine". Therefore "deletedChars" added.
			tokenIndex = searchLine.indexOf(token) + deletedChars;
			System.out.print(tokenIndex);
			writer.write(Integer.toString(tokenIndex));
			System.out.print(" > ");
			writer.write(" > ");
			final String beforeToken = line
			        .substring(0, tokenIndex);
			final String afterToken = line.substring(tokenIndex
			        + token.length());
			System.out.println(beforeToken + "**" + token + "**"
			        + afterToken);
			writer.write(beforeToken + "**" + token + "**"
			        + afterToken);
			writer.newLine();
			// add deleted chars of "searchLine" (see below) to
			// deletedChars
			deletedChars = deletedChars
			        + searchLine.subSequence(0,
			                searchLine.indexOf(token)).length()
			        + token.length();
			// delete found token and everything before eg.:
			// asdfsimpleasdf -> asdf
			searchLine = searchLine.substring(searchLine
			        .indexOf(token) + token.length());
			// +1 to found results in file
			foundFile++;
		    }

		    // next line => rowCounter++
		    rowCounter++;
		    line = reader.readLine();
		}

		// sum up all results in Files to "foundProject"
		// print & write summary
		foundProject = foundProject + foundFile;
		System.out.println(fileList.get(0) + " includes " + token + " "
		        + foundFile + " time(s)");
		writer.write(fileList.get(0) + " includes " + token + " "
		        + Integer.toString(foundFile) + " time(s)");
		writer.newLine();
		System.out.println("");
		writer.newLine();

	    }

	    // else, if "tmpFile" is folder -> subfolders and subfiles to
	    // "fileArray"
	    else {
		if (tmpFile.isDirectory()) {
		    fileArray = tmpFile.list();

		    // "fileArray" -> "fileList"
		    for (final String element : fileArray) {
			fileList.add(Paths.get(tmpFile + "/" + element));
		    }
		}
	    }
	    // remove processed file
	    fileList.remove(0);
	}

	System.out.println("The project includes " + token + " " + foundProject
	        + " time(s)");
	writer.write("The project includes " + token + " "
	        + Integer.toString(foundProject) + " time(s)");
	writer.newLine();
	writer.flush();
	writer.close();
    }

}
