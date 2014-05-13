package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "title", "id", "authors" })
public class Publication {

	private String title;
	private int yearPublished;
	private PublicationType type;
	private String id;

	private List<Author> authors = new LinkedList<>();

	public Publication() {
		super();
	}

	@XmlAttribute
	public PublicationType getType() {
		return type;
	}

	public void setType(PublicationType type) {
		this.type = type;
	}

	@XmlAttribute
	public int getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "author")
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return String.format(
				"[%s] The author(s) %s published %s as a %s in %d", id,
				getAuthorNames(), title, type, yearPublished);
	}

	private String getAuthorNames() {
		String authorNames = "";
		for (int i = 0; i < authors.size(); i++) {
			Author author = authors.get(i);
			authorNames += author.getName();

			if (i + 1 == authors.size()) {
				authorNames += ", ";
			}

		}
		return authorNames;
	}
}
