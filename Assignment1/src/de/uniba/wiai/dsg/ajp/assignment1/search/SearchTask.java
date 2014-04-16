package de.uniba.wiai.dsg.ajp.assignment1.search;

/**
 * Groups together all parameters for a search task and does not contain any logic.
 */
public class SearchTask {

	/**
	 * the root folder from which the search is started
	 */
	private String rootFolder;

	/**
	 * the extension of the files which are searched
	 */
	private String fileExtension;

	/**
	 * the string to be searched
	 */
	private String token;

	/**
	 * the path for the search result output
	 */
	private String resultFile;

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResultFile() {
		return resultFile;
	}

	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}

}
