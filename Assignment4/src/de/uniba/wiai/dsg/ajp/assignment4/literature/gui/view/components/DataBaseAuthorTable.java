package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.AuthorConfigurationView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.LiteratureDatabaseView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Author;
/**
 * the first half of the main view. consists of the table and two buttons
 * (add/remove).
 * 
 * @author mathias
 * 
 */
public class DataBaseAuthorTable extends JPanel {

	/** serial version. */
	private static final long serialVersionUID = -6797113448778036171L;

	/**
	 * Constructor to initate the components.
	 */
	public DataBaseAuthorTable() {
		addKeyListener(new DataBaseViewKeyListner());
		final JComponent buttonComp = new JPanel();
		buttonComp.addKeyListener(new DataBaseViewKeyListner());
		buttonComp.setLayout(new GridLayout(1, 2));

		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected author");
		deleteSelectedAuthorButton.addKeyListener(new DataBaseViewKeyListner());
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Author authorToRemove = null;
				LiteratureDatabaseView.removeAuthor(authorToRemove);

			}

		});

		buttonComp.add(deleteSelectedAuthorButton, BorderLayout.CENTER);

		final Button addAuthorButton = new Button("Create Author");
		addAuthorButton.addKeyListener(new DataBaseViewKeyListner());
		addAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new AuthorConfigurationView();

			}

		});
		buttonComp.add(addAuthorButton, BorderLayout.CENTER);
		final JComponent testFrame = new JLabel(
				"Please replace me. I am only a test Author.");
		// TODO replace with author table
		testFrame.addKeyListener(new DataBaseViewKeyListner());
		setLayout(new GridLayout(2, 1));

		add(testFrame);
		add(buttonComp);
	}

}
