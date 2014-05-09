package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;

public class DatabaseServiceImpl implements DatabaseService {

    private final Database dataBase;

    /**
     * Constructor.
     * 
     * @param dataBase
     *            the database to be serviced.
     */
    public DatabaseServiceImpl(final Database dataBase) {
	if (dataBase == null) {
	    throw new IllegalArgumentException("database is null.");
	}
	this.dataBase = dataBase;
    }

    /**
     * Constructors sets up a new empty database.
     */
    public DatabaseServiceImpl() {
	dataBase = new Database();
    }

    @Override
    public void addPublication(final String title, final int yearPublished,
	    final PublicationType type, final Author[] authors, final String id)
	    throws LiteratureDatabaseException {
	final Publication publication = new Publication();
	publication.setYearPublished(yearPublished);
	publication.setType(type);
	publication.setAuthors(Arrays.asList(authors));
	publication.setId(id);
	dataBase.getPublications().add(publication);
	// when any author is not yet in the list he/she is added
	for (final Author author : authors) {
	    // TODO this might not be implemented right
	    if (!dataBase.getAuthors().contains(author)) {
		dataBase.getAuthors().add(author);
	    }
	}

    }

    @Override
    public void removePublicationByID(final String id)
	    throws LiteratureDatabaseException {
	Publication publicationToRemove = null;
	for (final Publication publication : dataBase.getPublications()) {
	    if (publication.getId().equals(id)) {
		publicationToRemove = publication;
		break;
	    }
	}
	if (publicationToRemove == null) {
	    // not found
	    return;
	}
	for (final Author author : dataBase.getAuthors()) {
	    for (final Publication publication : author.getPublications()) {
		if (publication.getId().equals(id)) {
		    author.getPublications().remove(publication);
		    break;
		}
	    }
	}
    }

    @Override
    public void removeAuthorByID(final String id)
	    throws LiteratureDatabaseException {
	// TODO Auto-generated method stub

    }

    @Override
    public void addAuthor(final String name, final String email, final String id)
	    throws LiteratureDatabaseException {
	final Author authorToAdd = new Author();
	authorToAdd.setName(name);
	authorToAdd.setEmail(email);
	authorToAdd.setId(id);
	dataBase.getAuthors().add(authorToAdd);

    }

    @Override
    public Publication[] getPublications() {
	return dataBase.getPublications().toArray(
		new Publication[dataBase.getPublications().size()]);
    }

    @Override
    public Author[] getAuthors() {
	return dataBase.getAuthors().toArray(
		new Author[dataBase.getAuthors().size()]);
    }

    @Override
    public void clear() {
	dataBase.getAuthors().clear();
	dataBase.getPublications().clear();

    }

    @Override
    public void printXMLToConsole() throws LiteratureDatabaseException {

	try {
	    final JAXBContext context = JAXBContext.newInstance(Database.class);
	    final Marshaller ms = context.createMarshaller();
	    ms.marshal(dataBase, System.out);
	} catch (final MarshalException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	} catch (final JAXBException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	}

    }

    @Override
    public void saveXMLToFile(final String path)
	    throws LiteratureDatabaseException {
	try {
	    final JAXBContext context = JAXBContext.newInstance(Database.class);
	    final Marshaller ms = context.createMarshaller();
	    ms.marshal(dataBase, new File(path));
	} catch (final MarshalException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	} catch (final JAXBException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	}
    }

}
