package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Unmarshaller;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;

public class MainServiceImpl implements MainService {

    /**
     * Default constructor required for grading.
     */
    public MainServiceImpl() {
	/*
	 * DO NOT REMOVE - REQUIRED FOR GRADING
	 * 
	 * YOU CAN EXTEND IT BELOW THIS COMMENT
	 */
    }

    @Override
    public void validate(final String path) throws LiteratureDatabaseException {
	// TODO Auto-generated method stub

    }

    @Override
    public DatabaseService load(final String path)
	    throws LiteratureDatabaseException {
	try {
	    final JAXBContext context = JAXBContext.newInstance(Database.class);
	    final Unmarshaller ms = context.createUnmarshaller();
	    return (DatabaseService) ms.unmarshal(new File(path));
	} catch (final MarshalException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	} catch (final JAXBException e) {
	    // TODO add message
	    throw new LiteratureDatabaseException(e);
	}
    }

    @Override
    public DatabaseService create() throws LiteratureDatabaseException {
	return new DatabaseServiceImpl();
    }

}
