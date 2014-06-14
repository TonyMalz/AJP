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

public class DataBaseAuthorTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6797113448778036171L;

	public DataBaseAuthorTable() {
		final JComponent buttonComp = new JPanel();
		buttonComp.setLayout(new GridLayout(1, 2));

		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected author");
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO implement action for removing selected author

			}

		});

		buttonComp.add(deleteSelectedAuthorButton, BorderLayout.CENTER);

		final Button addAuthorButton = new Button("Create Author");
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
		setLayout(new GridLayout(2, 1));

		add(testFrame);
		add(buttonComp);
	}

}
