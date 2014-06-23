/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.LiteratureDatabaseController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;

/**
 * View to configure a new author to be added.<br>
 * You can input the id, name and email.<br>
 * Will warn and reinput you when the syntax of the id or email is not correct.<br>
 * Esc will dispose the view and ENTER will try to create the author.
 * 
 * @author mathias
 * 
 */
public class AuthorConfigurationView extends JFrame {
	/** serial version. */
	private static final long serialVersionUID = -6884370948256093402L;
	/** text field to input the id. */
	private final TextField idTextField;
	/** text field for the name. */
	private final TextField nameTextField;
	/** text field for the email. */
	private final TextField emailTextField;

	private final LiteratureDatabaseController controller;

	/**
	 * Constructor to initiate the components.
	 */
	public AuthorConfigurationView(final LiteratureDatabaseController controller) {
		this.controller = controller;
		addKeyListener(new AuthorConfigurationKeyListener());
		setTitle("Create Author");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(4, 2));
		this.add(new Label("ID:"));
		idTextField = new TextField("");
		idTextField.addKeyListener(new AuthorConfigurationKeyListener());
		this.add(idTextField);

		this.add(new Label("Name:"));
		nameTextField = new TextField("");
		nameTextField.addKeyListener(new AuthorConfigurationKeyListener());
		this.add(nameTextField);

		this.add(new Label("Email:"));
		emailTextField = new TextField("");
		emailTextField.addKeyListener(new AuthorConfigurationKeyListener());
		this.add(emailTextField);

		final Button createButton = new Button("Create Author");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				createAuthor();

			}

		});
		createButton.addKeyListener(new AuthorConfigurationKeyListener());
		this.add(createButton);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		validate();
		setVisible(true);
	}
	/**
	 * checks if the syntax of the id and email entered is correct. If so it
	 * tries to add the author to the database and dispose itself. When the
	 * syntax is not correct it will show error messages.
	 */
	private void createAuthor() {
		final String id = idTextField.getText();
		final String name = nameTextField.getText();
		final String email = emailTextField.getText();
		boolean authorValid = true;
		if (!ValidationHelper.isId(id)) {
			// TODO keep static or controller.showErrorMessage(String)?
			showErrorMessage(
					"The entered Id is not correct.\nPlease choose another one.",
					"Invalid input");
			authorValid = false;
		}
		if (!ValidationHelper.isEmail(email)) {
			showErrorMessage(
					"The entered email is not correct.\nPlease choose another one.",
					"Invalid input");
			authorValid = false;
		}
		if (!authorValid) {
			return;
		}
		final Author authorToAdd = new Author();
		authorToAdd.setEmail(email);
		authorToAdd.setId(id);
		authorToAdd.setName(name);

		controller.createAuthor(authorToAdd);
		dispose();

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

	/**
	 * The key Listener for the view to add an new author.<br>
	 * ENTER: create a new author. <br>
	 * ESC: dispose
	 * 
	 * @author mathias
	 * 
	 */
	public class AuthorConfigurationKeyListener implements KeyListener {

		@Override
		public void keyTyped(final KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					createAuthor();
					break;
				case KeyEvent.VK_ESCAPE :
					dispose();
					break;
				case KeyEvent.VK_I :
					// TODO add shortcuts
					break;
				case KeyEvent.VK_N :
					// TODO add shortcuts
					break;

				case KeyEvent.VK_E :
					// TODO add shortcuts
					break;
				// TODO remove just for testing
				case KeyEvent.VK_1 :
					idTextField.setText("test1");
					nameTextField.setText("Tester 1");
					emailTextField.setText("test.1@t.com");
					break;
				case KeyEvent.VK_2 :
					idTextField.setText("test2");
					nameTextField.setText("Tester 2");
					emailTextField.setText("test.2@t.com");
					break;
				case KeyEvent.VK_3 :
					idTextField.setText("test3");
					nameTextField.setText("Tester 3");
					emailTextField.setText("test.3@t.com");
					break;
				case KeyEvent.VK_4 :
					idTextField.setText("test4");
					nameTextField.setText("Tester 4");
					emailTextField.setText("test.4@t.com");
					break;
				case KeyEvent.VK_5 :
					idTextField.setText("test5");
					nameTextField.setText("Tester 5");
					emailTextField.setText("test.5@t.com");
					break;
				case KeyEvent.VK_6 :
					idTextField.setText("test6");
					nameTextField.setText("Tester 6");
					emailTextField.setText("test.6@t.com");
					break;
				case KeyEvent.VK_7 :
					idTextField.setText("test7");
					nameTextField.setText("Tester 7");
					emailTextField.setText("test.7@t.com");
					break;
				case KeyEvent.VK_8 :
					idTextField.setText("test8");
					nameTextField.setText("Tester 8");
					emailTextField.setText("test.8@t.com");
					break;
				case KeyEvent.VK_9 :
					idTextField.setText("test9");
					nameTextField.setText("Tester 9");
					emailTextField.setText("test.9@t.com");
			}
			repaint();

		}

		@Override
		public void keyReleased(final KeyEvent arg0) {

		}

		@Override
		public void keyPressed(final KeyEvent arg0) {

		}

	}
}
