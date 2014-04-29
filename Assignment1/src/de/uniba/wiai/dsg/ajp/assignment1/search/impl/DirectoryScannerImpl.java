package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Search recursively in given folder and optionally filter by file extension
 * 
 * @author Tony
 * 
 */
public class DirectoryScannerImpl implements DirectoryScanner {

    @Override
    public List<Path> scanFileSystem(Path root, String fileExtension) {
	// sanity checks
	if (root == null) {
	    throw new IllegalArgumentException("root path must not be null");
	}
	if (!Files.isReadable(root)) {
	    throw new IllegalArgumentException(
		    "root directory is not readable or does not exist");
	}

	if (!Files.isDirectory(root)) {
	    throw new IllegalArgumentException(
		    "root directory is not a directory");
	}

	// default to match all if fileExtension is null
	final String searchExtension = (fileExtension == null) ? ""
		: fileExtension;

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
		if (file.toString().endsWith(searchExtension)) {
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
	    Files.walkFileTree(root, fileTreeVisitor);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return fileList;
    }

}
