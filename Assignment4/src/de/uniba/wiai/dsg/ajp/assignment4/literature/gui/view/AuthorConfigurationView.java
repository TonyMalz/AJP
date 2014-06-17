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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.istack.internal.logging.Logger;

import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;

/**
 * @author mathias
 * 
 */
public class AuthorConfigurationView extends JFrame {
	Logger LOGGER = Logger.getLogger(AuthorConfigurationView.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -6884370948256093402L;
	private final TextField idTextField;
	private final TextField nameTextField;
	private final TextField emailTextField;

	/**
	 * @throws HeadlessException
	 */
	public AuthorConfigurationView() {
		setTitle("Create Author");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(4, 2));
		this.add(new Label("ID:"));
		idTextField = new TextField("");
		this.add(idTextField);

		this.add(new Label("Name:"));
		nameTextField = new TextField("");
		this.add(nameTextField);

		this.add(new Label("Email:"));
		emailTextField = new TextField("");
		this.add(emailTextField);

		final Button createButton = new Button("Create Author");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				createAuthor();

			}

		});
		this.add(createButton);
		// TODO make key listener work
		addKeyListener(new KeyListener() {

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
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		validate();
		setVisible(true);
	}
	private void createAuthor() {
		final String id = idTextField.getText();
		final String name = nameTextField.getText();
		final String email = emailTextField.getText();
		boolean authorValid = true;
		if (!ValidationHelper.isId(id)) {
			JOptionPane
					.showMessageDialog(
							new Frame(),
							"The entered Id is not correct.\nPlease choose another one.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
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

		// TODO add connection to controller
		showInfoMessage("Adding the author: " + authorToAdd.toString(),
				"Just for testing");

		dispose();

	}
	public void showErrorMessage(final String message, final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public void showInfoMessage(final String message, final String title) {
		JOptionPane.showMessageDialog(new Frame(), message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(final String[] args) {
		new AuthorConfigurationView();
	}
}
