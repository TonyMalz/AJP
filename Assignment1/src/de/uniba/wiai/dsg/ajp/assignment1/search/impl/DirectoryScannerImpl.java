package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.uniba.wiai.dsg.ajp.assignment1.search.DirectoryScanner;
import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

/**
 * Search recursively in given folder and optionally filter by file extension
 * 
 * @author Tony
 * 
 */
public class DirectoryScannerImpl implements DirectoryScanner {

    private SearchTask task;

    public DirectoryScannerImpl(final SearchTask task) {
	Objects.requireNonNull(task, "search task is null");
	this.task = task;
    }

    /**
     * Returns list of file paths within the given root folder. Optionally
     * confines results by a given file extension.
     * 
     * @return the list of matching file paths
     * @throws TokenFinderException
     *             in case an error occurs while trying to traverse the file
     *             tree
     */
    @Override
    public List<Path> getFilePaths() throws TokenFinderException {

	Objects.requireNonNull(task.getRootFolder(), "root folder is null");

	final Path startDirectory = Paths.get(task.getRootFolder());

	// sanity checks
	if (!Files.isReadable(startDirectory)) {
	    throw new TokenFinderException(
		    "starting directory is not readable or does not exist: "
			    + startDirectory);
	}
	if (!Files.isDirectory(startDirectory)) {
	    throw new TokenFinderException(
		    "starting directory is not a directory: " + startDirectory);
	}

	// default to 'match all' if fileExtension is null
	final String searchForExtension = (task.getFileExtension() == null) ? ""
		: task.getFileExtension();

	final List<Path> fileList = new ArrayList<>();
	FileVisitor<Path> fileTreeVisitor = new FileVisitor<Path>() {

	    @Override
	    public FileVisitResult preVisitDirectory(Path dir,
		    BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	    }

	    @Override
	    public FileVisitResult visitFile(Path file,
		    BasicFileAttributes attrs) throws IOException {

		if (file.toString().endsWith(searchForExtension)) {
		    fileList.add(file);
		}
		return FileVisitResult.CONTINUE;
	    }

	    @Override
	    public FileVisitResult visitFileFailed(Path file, IOException exc)
		    throws IOException {
		return FileVisitResult.CONTINUE;
	    }

	    @Override
	    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
		    throws IOException {
		return FileVisitResult.CONTINUE;
	    }
	};

	try {
	    Files.walkFileTree(startDirectory, fileTreeVisitor);
	} catch (SecurityException e) {
	    throw new TokenFinderException("Access denied to starting folder: "
		    + startDirectory);
	} catch (IOException e) {
	    throw new TokenFinderException(e.getMessage());
	}

	return fileList;
    }

}
