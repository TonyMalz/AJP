package de.uniba.wiai.dsg.ajp.assignment4.literature.logic.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Database;

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
	public void validate(String path) throws LiteratureDatabaseException {
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = sf.newSchema(new File("schema1.xsd"));
			schema.newValidator().validate(new StreamSource(new File(path)));
		} catch (SAXException | IOException e) {
			throw new LiteratureDatabaseException("path " + path
					+ " could not be validated", e);
		}
	}

	@Override
	public DatabaseService load(String path) throws LiteratureDatabaseException {
		return new DatabaseServiceImpl(JAXB.unmarshal(new File(path),
				Database.class));
	}

	@Override
	public DatabaseService create() throws LiteratureDatabaseException {
		return new DatabaseServiceImpl(new Database());
	}

}
