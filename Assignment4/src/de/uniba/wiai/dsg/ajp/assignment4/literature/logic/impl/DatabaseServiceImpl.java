package de.uniba.wiai.dsg.ajp.assignment4.literature.logic.impl;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import javax.xml.bind.JAXB;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.PublicationType;

public class DatabaseServiceImpl implements DatabaseService {

	private Database database;

	public DatabaseServiceImpl(Database database) {
		super();
		this.database = database;
	}

	@Override
	public void addPublication(String title, int yearPublished,
			PublicationType type, Author[] authors, String id)
			throws LiteratureDatabaseException {

		if (title == null) {
			throw new LiteratureDatabaseException("title is null");
		}

		if (title.isEmpty()) {
			throw new LiteratureDatabaseException("title is empty");
		}

		validateId(id);

		if (type == null) {
			throw new LiteratureDatabaseException("type is null");
		}

		if (yearPublished < 0) {
			throw new LiteratureDatabaseException(
					"year published is less than zero");
		}

		if (authors == null) {
			throw new LiteratureDatabaseException("authors array is null");
		}

		if (authors.length == 0) {
			throw new LiteratureDatabaseException("authors array is empty");
		}

		assertNoNullValuesInArray(authors);
		assertNoDuplicateEntriesInArray(authors);

		checkUniquenessOfPublicationId(id);

		Publication publication = new Publication();
		publication.setTitle(title);
		publication.setId(id);
		publication.setYearPublished(yearPublished);
		publication.setType(type);
		publication.setAuthors(Arrays.asList(authors));

		database.getPublications().add(publication);

		for (Author author : authors) {
			author.getPublications().add(publication);
		}
	}

	private void assertNoDuplicateEntriesInArray(Author[] authors)
			throws LiteratureDatabaseException {
		for (Author author : authors) {
			int duplicates = 0;
			for (Author otherAuthor : authors) {

				if (author == otherAuthor) {
					duplicates++;
				}

				if (duplicates > 1) {
					throw new LiteratureDatabaseException(
							"duplicate authors found in authors array");
				}
			}
		}
	}

	private void assertNoNullValuesInArray(Author[] authors)
			throws LiteratureDatabaseException {
		boolean isValid = true;
		for (Author author : authors) {
			if (author == null) {
				isValid = false;
			}
		}
		if (!isValid) {
			throw new LiteratureDatabaseException(
					"null value found in authors array");
		}
	}

	private void checkUniquenessOfPublicationId(String id)
			throws LiteratureDatabaseException {
		
		validateId(id);
		
		for (Publication publication : database.getPublications()) {
			if (publication.getId().equals(id)) {
				throw new LiteratureDatabaseException("Publication with ID "
						+ id + " is already added");
			}
		}
	}

	@Override
	public void removePublicationByID(String id)
			throws LiteratureDatabaseException {

		validateId(id);

		Publication publication = findPublicationByID(id);
		database.getPublications().remove(publication);

		for (Author author : publication.getAuthors()) {
			author.getPublications().remove(publication);
		}
	}

	private Publication findPublicationByID(String id)
			throws LiteratureDatabaseException {

		validateId(id);

		for (Publication publication : database.getPublications()) {
			if (publication.getId().equals(id)) {
				return publication;
			}
		}
		throw new LiteratureDatabaseException(
				"cannot find publication with ID " + id);
	}

	@Override
	public void removeAuthorByID(String id) throws LiteratureDatabaseException {

		validateId(id);

		Author author = findAuthorByID(id);
		if (author.getPublications().isEmpty()) {
			database.getAuthors().remove(author);
		} else {
			throw new LiteratureDatabaseException("author with ID " + id
					+ " still has publications");
		}

	}

	private Author findAuthorByID(String id) throws LiteratureDatabaseException {
		for (Author author : database.getAuthors()) {
			if (author.getId().equals(id)) {
				return author;
			}
		}
		throw new LiteratureDatabaseException("cannot find author with ID "
				+ id);
	}

	@Override
	public void addAuthor(String name, String email, String id)
			throws LiteratureDatabaseException {

		if (name == null || name.isEmpty()) {
			throw new LiteratureDatabaseException("name is null");
		}

		if (name.isEmpty()) {
			throw new LiteratureDatabaseException("name is empty");
		}

		validateEmail(email);
		validateId(id);

		checkUniquenessOfAuthorId(id);

		Author author = new Author();
		author.setName(name);
		author.setEmail(email);
		author.setId(id);
		database.getAuthors().add(author);
	}

	private void validateEmail(String email) throws LiteratureDatabaseException {
		if (email == null) {
			throw new LiteratureDatabaseException("email is null");
		}
		if (email.isEmpty()) {
			throw new LiteratureDatabaseException("email is empty");
		}
		if (!ValidationHelper.isEmail(email)) {
			throw new LiteratureDatabaseException("email " + email
					+ " is invalid");
		}
	}

	private void validateId(String id) throws LiteratureDatabaseException {
		if (id == null) {
			throw new LiteratureDatabaseException("ID is null");
		}
		if (id.isEmpty()) {
			throw new LiteratureDatabaseException("ID is empty");
		}
		if (!ValidationHelper.isId(id)) {
			throw new LiteratureDatabaseException("ID " + id + " is invalid");
		}
	}

	private void checkUniquenessOfAuthorId(String id)
			throws LiteratureDatabaseException {
		for (Author author : database.getAuthors()) {
			if (author.getId().equals(id)) {
				throw new LiteratureDatabaseException("Author with ID " + id
						+ " is already added");
			}
		}
	}

	@Override
	public Publication[] getPublications() {
		return database.getPublications().toArray(new Publication[] {});
	}

	@Override
	public Author[] getAuthors() {
		return database.getAuthors().toArray(new Author[] {});
	}

	@Override
	public void clear() {
		database.setAuthors(new LinkedList<Author>());
		database.setPublications(new LinkedList<Publication>());
	}

	@Override
	public void printXMLToConsole() throws LiteratureDatabaseException {
		JAXB.marshal(database, System.out);
	}

	@Override
	public void saveXMLToFile(String path) throws LiteratureDatabaseException {
		if (path == null || path.isEmpty()) {
			throw new LiteratureDatabaseException();
		}
		JAXB.marshal(database, new File(path));
	}

}
