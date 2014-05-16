package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

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
		try {
			final SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = sf.newSchema(new File("./schema.xsd"));
			final Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
		} catch (final SAXException e) {
			throw new LiteratureDatabaseException(
					"An internal error occured, while trying to validate the database.",
					e);
		} catch (final IOException e) {
			throw new LiteratureDatabaseException(
					"An internal error occured, while trying to validate the database.",
					e);
		}

	}

	@Override
	public DatabaseService load(final String path)
			throws LiteratureDatabaseException {
		try {
			final JAXBContext context = JAXBContext.newInstance(Database.class);
			final Unmarshaller ms = context.createUnmarshaller();
			// return (DatabaseService) ms.unmarshal(new File(path));//�NDERUNG
			return new DatabaseServiceImpl((Database) ms.unmarshal(new File(
					path)));
		} catch (final MarshalException e) {
			// TODO add message
			throw new LiteratureDatabaseException(e);
		} catch (final JAXBException e) {
			// TODO add message
			throw new LiteratureDatabaseException(e);
		}
		// TODO ms.unmarshal throws this exception see javadoc
		// catch (final UnmarshalException e) {
		// throw new LiteratureDatabaseException(e);
		// }
	}

	@Override
	public DatabaseService create() {
		return new DatabaseServiceImpl();
	}

}
