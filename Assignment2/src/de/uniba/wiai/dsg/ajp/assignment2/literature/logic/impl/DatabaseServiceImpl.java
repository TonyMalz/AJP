package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;

public class DatabaseServiceImpl implements DatabaseService {
    private final Database database;

    public DatabaseServiceImpl() {
	this.database = new Database();
    }

    public DatabaseServiceImpl(Database database) {
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

	if (yearPublished < 0) {
	    throw new LiteratureDatabaseException("yearPublished is negative");
	}

	if (type == null) {
	    throw new LiteratureDatabaseException("type is null");
	}

	if (id == null) {
	    throw new LiteratureDatabaseException("id is null");
	}
	if (id.isEmpty()) {
	    throw new LiteratureDatabaseException("id is empty");
	}
	if (!ValidationHelper.isId(id)) {
	    throw new LiteratureDatabaseException("id is not valid");
	}

	for (final Publication publication : database.getPublications()) {
	    if (publication.getId().equals(id)) {
		throw new LiteratureDatabaseException("id is already used");
	    }
	}

	if (authors == null) {
	    throw new LiteratureDatabaseException("authors is null");
	}

	if (authors.length == 0) {
	    throw new LiteratureDatabaseException("authors is empty");
	}

	// check for duplicate author ids
	Set<String> uniqAuthors = new HashSet<>();
	for (int i = 0; i < authors.length; i++) {
	    if (!uniqAuthors.add(authors[i].getId()))
		throw new LiteratureDatabaseException(
			"authors contains duplicates");
	}

	final Publication publication = new Publication();
	publication.setYearPublished(yearPublished);
	publication.setType(type);
	publication.setAuthors(Arrays.asList(authors));
	publication.setId(id);
	publication.setTitle(title);
	database.getPublications().add(publication);
	// if any author is not yet in the list he/she is added
	for (final Author author : authors) {
	    if (!database.getAuthors().contains(author)) {
		database.getAuthors().add(author);
	    }
	}
	addPublicationToAuthor(authors, publication);
    }

    private void addPublicationToAuthor(Author[] authorsToAdd,
	    Publication publication) {
	for (int i = 0; i < authorsToAdd.length; i++) {
	    for (Author nextAuthor : database.getAuthors()) {
		if (nextAuthor.getId().equals(authorsToAdd[i].getId())) {
		    nextAuthor.getPublications().add(publication);
		}
	    }
	}
    }

    @Override
    public void removePublicationByID(String id)
	    throws LiteratureDatabaseException {
	if (id == null) {
	    throw new LiteratureDatabaseException("id is null");
	}
	if (id.isEmpty()) {
	    throw new LiteratureDatabaseException("id is empty");
	}
	if (!ValidationHelper.isId(id)) {
	    throw new LiteratureDatabaseException("id is not valid");
	}

	for (final Publication publication : database.getPublications()) {
	    if (publication.getId().equals(id)) {
		removePublicationFromAuthors(publication,
			publication.getAuthors());
		database.getPublications().remove(publication);
		return;
	    }
	}
	throw new LiteratureDatabaseException("id not found");
    }

    private void removePublicationFromAuthors(Publication publication,
	    List<Author> authorsToRemoveFrom) {
	for (Author author : database.getAuthors()) {
	    if (authorsToRemoveFrom.contains(author)) {
		author.getPublications().remove(publication);
	    }
	}
    }

    @Override
    public void removeAuthorByID(String id) throws LiteratureDatabaseException {
	if (id == null) {
	    throw new LiteratureDatabaseException("id is null");
	}
	if (id.isEmpty()) {
	    throw new LiteratureDatabaseException("id is empty");
	}
	if (!ValidationHelper.isId(id)) {
	    throw new LiteratureDatabaseException("id is not valid");
	}

	boolean foundAuthor = false;
	List<Publication> pubsToDelete = new ArrayList<>();

	for (Author author : database.getAuthors()) {
	    if (author.getId().equals(id)) {
		for (Publication publication : author.getPublications()) {
		    publication.getAuthors().remove(author);
		    if (publication.getAuthors().isEmpty()) {
			pubsToDelete.add(publication);
		    }
		}
		database.getAuthors().remove(author);
		foundAuthor = true;
		break;
	    }
	}
	if (!foundAuthor)
	    throw new LiteratureDatabaseException("id not found");

	database.getPublications().removeAll(pubsToDelete);
    }

    @Override
    public void addAuthor(String name, String email, String id)
	    throws LiteratureDatabaseException {

	if (name == null) {
	    throw new LiteratureDatabaseException("author name is null");
	}
	if (name.isEmpty()) {
	    throw new LiteratureDatabaseException("author name is empty");
	}

	if (email == null) {
	    throw new LiteratureDatabaseException("email is null");
	}
	if (email.isEmpty()) {
	    throw new LiteratureDatabaseException("email is empty");
	}

	if (id == null) {
	    throw new LiteratureDatabaseException("id is null");
	}
	if (id.isEmpty()) {
	    throw new LiteratureDatabaseException("id is empty");
	}
	if (!ValidationHelper.isId(id)) {
	    throw new LiteratureDatabaseException("id is not valid");
	}
	for (final Author author : database.getAuthors()) {
	    if (author.getId().equals(id)) {
		throw new LiteratureDatabaseException("id is already used");
	    }
	}

	final Author authorToAdd = new Author();
	authorToAdd.setName(name);
	authorToAdd.setEmail(email);
	authorToAdd.setId(id);
	database.getAuthors().add(authorToAdd);

    }

    @Override
    public Publication[] getPublications() {
	return database.getPublications().toArray(
		new Publication[database.getPublications().size()]);
    }

    @Override
    public Author[] getAuthors() {
	return database.getAuthors().toArray(
		new Author[database.getAuthors().size()]);
    }

    @Override
    public void clear() {
	database.getAuthors().clear();
	database.getPublications().clear();

    }

    @Override
    public void printXMLToConsole() throws LiteratureDatabaseException {
	try {
	    final JAXBContext context = JAXBContext.newInstance(Database.class);
	    final Marshaller ms = context.createMarshaller();
	    ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    ms.marshal(database, System.out);
	} catch (final MarshalException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(
		    "An internal error occured, while trying to convert database to XML. ",
		    e);
	} catch (final JAXBException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(
		    "An internal error occured, while trying to convert database to XML. ",
		    e);
	}

    }

    @Override
    public void saveXMLToFile(String path) throws LiteratureDatabaseException {
	if (path == null) {
	    throw new LiteratureDatabaseException("Given file path is null");
	}
	if (path.isEmpty()) {
	    throw new LiteratureDatabaseException("Given file path is empty");
	}

	try {
	    final JAXBContext context = JAXBContext.newInstance(Database.class);
	    final Marshaller ms = context.createMarshaller();
	    ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    ms.marshal(database, new File(path));
	} catch (final MarshalException e) {
	    throw new LiteratureDatabaseException(
		    "An internal error occured, while trying to convert database to XML. ",
		    e);
	} catch (final JAXBException e) {
	    throw new LiteratureDatabaseException(
		    "An internal error occured, while trying to convert database to XML. ",
		    e);
	}

    }
}
