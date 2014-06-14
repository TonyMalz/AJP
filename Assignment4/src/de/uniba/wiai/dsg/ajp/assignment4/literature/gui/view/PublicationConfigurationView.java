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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.PublicationType;

/**
 * @author mathias
 * 
 */
public class PublicationConfigurationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7064747956754582339L;
	private final TextField yearTextField;
	private final TextField titleTextField;
	private final TextField idTextField;
	private final JComboBox<PublicationType> typeChoice;
	/**
	 * @throws HeadlessException
	 */
	public PublicationConfigurationView(final Author[] authorsToChose)
			throws HeadlessException {
		setTitle("Create Author");
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

		// TODO add choice for author

		final Button createButton = new Button("Create Button");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				createAuthor();

			}

		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		validate();
		setVisible(true);
	}
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
				JOptionPane
						.showMessageDialog(
								new Frame(),
								"The entered year is not correct.\nPlease choose another one.",
								"Invalid input", JOptionPane.ERROR_MESSAGE);
				publicationValid = false;
			}
		} catch (final NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							new Frame(),
							"The entered year is not correct.\nPlease choose another one.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
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
	}
	public static void main(final String[] args) {
		new PublicationConfigurationView(new Author[0]);
	}

}
