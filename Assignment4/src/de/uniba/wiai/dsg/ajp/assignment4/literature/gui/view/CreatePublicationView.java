package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import de.uniba.wiai.dsg.ajp.assignment4.literature.controller.CreatePublicationController;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.controller.MainViewWindowListener;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.model.AuthorListModel;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.PublicationType;
import de.uniba.wiai.dsg.ajp.assignment4.literature.model.DatabaseModel;
/**
 * view to create a new publication.
 * 
 * @author tony
 * 
 */
public class CreatePublicationView extends JDialog {
	/** controller to create a new publication. */
	CreatePublicationController controller;
	/** model of the database. */
	DatabaseModel model;
	/** text field of the id. */
	JTextField id;
	/** text field of the title. */
	JTextField title;
	/** text field of the year. */
	JFormattedTextField year;
	/** the selection field for the type. */
	JComboBox<PublicationType> publicationType;
	/** the selection field or the authors. */
	JList<String> authors;
	/**
	 * Constructor.
	 * 
	 * @param controller
	 *            for creating a publication.
	 */
	public CreatePublicationView(final CreatePublicationController controller) {
		super();
		this.controller = controller;
		initComponents();
		addWindowListener(new MainViewWindowListener());
	}
	/**
	 * Constructor.
	 * 
	 * @param parentView
	 *            main view.
	 * @param controller
	 *            for creating a new publication
	 * @param model
	 *            the main model.
	 */
	public CreatePublicationView(final MainView parentView,
			final CreatePublicationController controller,
			final DatabaseModel model) {
		super(parentView, true);

		this.controller = controller;
		this.model = model;
		initComponents();
	}
	/**
	 * generates the componenets.
	 */
	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Create Publication");
		setSize(new Dimension(300, 380));
		setContentPane(getCreateAuthorForm());
		setLocationRelativeTo(null);
	}
	/**
	 * creates and lay the fields out.
	 * 
	 * @return the beautiful form
	 */
	private JPanel getCreateAuthorForm() {
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Create publication"));
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
		panel.add(new JLabel("ID:"), c);

		// distribute 90% of available width to right column
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		id = new JTextField();
		panel.add(id, c);
		// reset to default
		c.weightx = 0;

		// TITLE
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(new JLabel("Title:"), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		title = new JTextField();
		panel.add(title, c);

		// YEAR
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(new JLabel("Year:"), c);
		c.fill = GridBagConstraints.NONE;
		// align left
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		final NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setMaximumIntegerDigits(4);
		myFormat.setParseIntegerOnly(true);
		myFormat.setGroupingUsed(false);
		year = new JFormattedTextField(myFormat);
		year.setColumns(3);
		year.setMinimumSize(new Dimension(40, 20));
		year.validate();
		panel.add(year, c);

		// TYPE
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(new JLabel("Type:"), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;

		publicationType = new JComboBox<>(PublicationType.values());
		panel.add(publicationType, c);

		// AUTHORS
		c.insets = new Insets(10, 0, 0, 10);
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(new JLabel("Authors:"), c);

		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		authors = new JList<>(new AuthorListModel(model.getAuthorModel()));
		authors.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(final int index0, final int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});
		panel.add(new JScrollPane(authors), c);

		// CREATE BUTTON
		c.weighty = 0.;
		c.gridx = 1;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		final JButton b = new JButton("Create publication");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Publication pub = new Publication();
				pub.setId(id.getText());
				pub.setTitle(title.getText());
				try {
					pub.setYearPublished(year.getValue() != null ? ((Long) year
							.getValue()).intValue() : -1);
				} catch (final ClassCastException ex) {
					pub.setYearPublished(-1);
				}

				pub.setAuthors(getSelectedAuthors());
				pub.setType((PublicationType) publicationType.getSelectedItem());
				controller.createPublication(pub);
			}

		});
		panel.add(b, c);

		return panel;
	}
	/**
	 * get a list of the selectable authors.
	 * 
	 * @return the list.
	 */
	private List<Author> getSelectedAuthors() {
		final List<Author> list = new LinkedList<>();
		for (final int idx : authors.getSelectedIndices()) {
			for (int i = 0; i < model.getAuthorModel().getAuthors().length; i++) {
				if (idx == i) {
					list.add(model.getAuthorModel().getAuthors()[i]);
					break;
				}
			}
		}
		return list;
	}
	/**
	 * shows the standard error message.
	 * 
	 * @param message
	 *            to be displayed.
	 */
	public void showErrorMessage(final String message) {
		JOptionPane.showMessageDialog(this, message, "Sorry",
				JOptionPane.ERROR_MESSAGE);
	}
}
