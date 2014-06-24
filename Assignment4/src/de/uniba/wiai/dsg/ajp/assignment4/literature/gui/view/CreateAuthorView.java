package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.CreateAuthorController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
/**
 * view to create a new author.
 * 
 * @author tony
 * 
 */
public class CreateAuthorView extends JDialog {
	/** controller for creating a new author. */
	CreateAuthorController controller;
	/** text field for the id. */
	JTextField id;
	/** text field for the email. */
	JTextField email;
	/** text field for the name. */
	JTextField name;
	/**
	 * Constructor.
	 * 
	 * @param controller
	 *            of this view.
	 */
	public CreateAuthorView(final CreateAuthorController controller) {
		super();
		this.controller = controller;
		initComponents();
	}
	/**
	 * Constructor.
	 * 
	 * @param parentView
	 *            the main view.
	 * @param controller
	 *            to create a new author.
	 */
	public CreateAuthorView(final MainView parentView,
			final CreateAuthorController controller) {
		// set parent view and make dialog modal
		super(parentView, true);

		this.controller = controller;
		initComponents();
	}
	/**
	 * creates the components.
	 */
	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Create Author");
		setSize(new Dimension(300, 200));
		setLocationRelativeTo(null);
		setContentPane(getCreateAuthorForm());
	}

	private JPanel getCreateAuthorForm() {
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Create author"));
		panel.setLayout(new GridBagLayout());

		final GridBagConstraints c = new GridBagConstraints();
		// column padding
		c.insets = new Insets(5, 0, 0, 10);

		// ID
		// distribute 1% of available width to left column
		c.weightx = 0.1;
		// column
		c.gridx = 0;
		// row
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		// align right
		c.anchor = GridBagConstraints.LINE_END;
		final JLabel idLabel = new JLabel("ID:");
		panel.add(idLabel, c);

		// distribute 90% of available width to right column
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		id = new JTextField();
		panel.add(id, c);
		// reset to default
		c.weightx = 0;

		// NAME
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		final JLabel nameLabel = new JLabel("Name:");
		panel.add(nameLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		name = new JTextField();
		panel.add(name, c);

		// EMAIL
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		final JLabel emailLabel = new JLabel("Email:");
		panel.add(emailLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		email = new JTextField();
		panel.add(email, c);

		// CREATE BUTTON
		c.gridx = 1;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		final JButton b = new JButton("Create author");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.createAuthor(getAuthor());
			}

		});
		panel.add(b, c);

		return panel;
	}

	/**
	 * shows the standard error message.
	 * 
	 * @param message
	 *            to be displayed
	 */
	public void showErrorMessage(final String message) {
		JOptionPane.showMessageDialog(this, message, "Sorry",
				JOptionPane.ERROR_MESSAGE);
	}
	public Author getAuthor() {
		final Author author = new Author();
		author.setId(id.getText());
		author.setName(name.getText());
		author.setEmail(email.getText());
		return author;
	}
}
