package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "email", "id", "name", "publications" })
public class Author {

	private String id;

	private String name;

	private String email;

	private List<Publication> publications = new LinkedList<>();

	public Author() {
		super();
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "publication")
	@XmlIDREF
	public List<Publication> getPublications() {
		return publications;

	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s (%s) published %d publication(s): %s",
				id, name, email, publications.size(), getPublicationIds());
	}

	private String getPublicationIds() {
		String publicationIds = "";
		for (int i = 0; i < publications.size(); i++) {
			Publication publication = publications.get(i);
			publicationIds += publication.getId();

			if (i + 1 != publications.size()) {
				publicationIds += ", ";
			}

		}
		return publicationIds;
	}

}
