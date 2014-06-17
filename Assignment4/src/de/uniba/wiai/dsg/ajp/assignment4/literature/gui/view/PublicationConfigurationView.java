/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.sun.istack.internal.logging.Logger;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.PublicationType;

/**
 * @author mathias
 * 
 */
public class PublicationConfigurationView extends JFrame {
	Logger LOGGER = Logger.getLogger(PublicationConfigurationView.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -7064747956754582339L;
	private static final Component authorsToSelect = null;
	private final TextField yearTextField;
	private final TextField titleTextField;
	private final TextField idTextField;
	private final JComboBox<PublicationType> typeChoice;
	private final List<JCheckBox> authorToSelect = new ArrayList<>();
	private final Author[] authorsToChose;
	/**
	 * @throws HeadlessException
	 */
	public PublicationConfigurationView(final Author[] authorsToChose)
			throws HeadlessException {
		setTitle("Create Publication");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(6, 2));
		this.add(new Label("ID:"));
		idTextField = new TextField("");
		this.add(idTextField);

		this.add(new Label("Title:"));
		titleTextField = new TextField("");
		this.add(titleTextField);

		this.add(new Label("Year:"));
		yearTextField = new TextField("");
		this.add(yearTextField);

		this.add(new Label("Type:"));
		typeChoice = new JComboBox<>(PublicationType.values());
		this.add(typeChoice);

		this.add(new Label("Author:"));
		this.authorsToChose = authorsToChose;
		if (authorsToChose != null) {
			for (final Author author : authorsToChose) {
				authorToSelect.add(new JCheckBox(author.getName() + " ("
						+ author.getId() + ")", false));
			}
		}
		final JPanel authorsPanel = new JPanel();
		authorsPanel.setLayout(new GridLayout(0, 1));
		for (final JCheckBox authorbox : authorToSelect) {
			authorsPanel.add(authorbox);
		}
		final JScrollPane authorsComp = new JScrollPane(authorsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(authorsComp);

		final Button createButton = new Button("Create Publication");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				createAuthor();

			}

		});
		add(createButton);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		validate();
		setVisible(true);
		// TODO uncoment when ready to handle
		// if (authorsToChose == null || authorsToChose.length == 0) {
		// dispose();
		// LiteratureDatabaseView
		// .showErrorMessage(
		// "No authors to select are available.\n You must first add a new Author.",
		// "No Authors");
		// }
	}
	/**
	 * tries to create a new Publication.
	 */
	private void createAuthor() {
		final String id = idTextField.getText();
		final String title = titleTextField.getText();
		final String year = yearTextField.getText();
		final PublicationType type = (PublicationType) typeChoice
				.getSelectedItem();
		boolean publicationValid = true;
		if (!ValidationHelper.isId(id)) {
			JOptionPane
					.showMessageDialog(
							new Frame(),
							"The entered Id is not correct.\nPlease choose another one.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
			publicationValid = false;
		}
		int yearPublished = 0;
		try {
			yearPublished = Integer.parseInt(year);
			// TODO auslagern in Controller?
			if (yearPublished < 0) {
				LiteratureDatabaseView
						.showErrorMessage(
								"The entered year is not correct.\nPlease choose another one.",
								"Invalid input");
				publicationValid = false;
			}
		} catch (final NumberFormatException e) {
			LiteratureDatabaseView
					.showErrorMessage(
							"The entered year is not correct.\nPlease choose another one.",
							"Invalid input");
			publicationValid = false;
		}
		final List<Author> selectedAuthors = new ArrayList<>();
		for (int i = 0; i < authorToSelect.size(); i++) {
			if (authorToSelect.get(i).isSelected()) {
				selectedAuthors.add(authorsToChose[i]);
			}
		}
		if (selectedAuthors.size() == 0) {
			LiteratureDatabaseView.showErrorMessage(
					"No selected authors.\nYou must at least chose one.",
					"Invalid Input");
			publicationValid = false;
		}
		if (!publicationValid) {
			// When anything is not valid nothing is added
			return;
		}

		final Publication pubToAdd = new Publication();
		pubToAdd.setId(id);
		pubToAdd.setTitle(title);
		pubToAdd.setYearPublished(yearPublished);
		pubToAdd.setType(type);
		pubToAdd.setAuthors(selectedAuthors);
		LiteratureDatabaseView.createPublication(pubToAdd);
	}
	public static void main(final String[] args) {
		final Author[] authors = new Author[4];
		authors[0] = new Author();
		authors[0].setEmail("a@b.de");
		authors[0].setId("abc");
		authors[0].setName("manuel test");
		authors[1] = new Author();
		authors[1].setEmail("c@d.de");
		authors[1].setId("xyz");
		authors[1].setName("anita test");

		authors[2] = new Author();
		authors[2].setEmail("hul@la.de");
		authors[2].setId("babba");
		authors[2].setName("babba test");

		authors[3] = new Author();
		authors[3].setEmail("m@m.de");
		authors[3].setId("mat");
		authors[3].setName("anita test");
		new PublicationConfigurationView(authors);
	}

}
