/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
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

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.PublicationType;

/**
 * @author mathias
 * 
 */
public class PublicationConfigurationView extends JFrame {
	/** serial version. */
	private static final long serialVersionUID = -7064747956754582339L;
	/** the controller. */
	private final LiteratureDatabaseController controller;
	/** the text field for the published year. */
	private final TextField yearTextField;
	/** the text field for the title. */
	private final TextField titleTextField;
	/** the text fied for the id. */
	private final TextField idTextField;
	/** the choice for the Publication type. */
	private final JComboBox<PublicationType> typeChoice;
	/** the selecton field for the authors. */
	private final List<JCheckBox> authorToSelect = new ArrayList<>();
	/** authros to chose from. */
	private final Author[] authorsToChose;
	/**
	 * Constructor initiates the view.
	 */
	public PublicationConfigurationView(final Author[] authorsToChose,
			final LiteratureDatabaseController controller)
			throws HeadlessException {
		this.controller = controller;

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
				createPublication();

			}

		});
		add(createButton);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		validate();
		setVisible(true);
		if (authorsToChose == null || authorsToChose.length == 0) {
			dispose();
			showErrorMessage(
					"No authors to select are available.\n You must first add a new Author.",
					"No Authors");
		}
	}
	/**
	 * tries to create a new Publication.
	 */
	private void createPublication() {
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
			if (yearPublished < 0) {
				showErrorMessage(
						"The entered year is not correct.\nPlease choose another one.",
						"Invalid input");
				publicationValid = false;
			}
		} catch (final NumberFormatException e) {
			showErrorMessage(
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
			showErrorMessage(
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
		dispose();
		controller.createPublication(pubToAdd);

	}
	/**
	 * Shows the default error message
	 * 
	 * @param message
	 *            to be displayed
	 * @param title
	 *            of the error window
	 */
	private static void showErrorMessage(final String message,
			final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.ERROR_MESSAGE);

	}

}
