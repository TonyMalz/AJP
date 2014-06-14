package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.PublicationConfigurationView;

public class DatabasePublicationTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1096171845816582557L;

	public DatabasePublicationTable() {
		final JPanel buttonsComp = new JPanel();
		buttonsComp.setLayout(new GridLayout(1, 2));
		// TODO add publication Table
		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected publication");
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				// TODO implement action for removing selected author

			}

		});

		buttonsComp.add(deleteSelectedAuthorButton);
		final Button addPubButton = new Button("Create Publication");
		addPubButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new PublicationConfigurationView(null);;
				// TODO change to select authors

			}

		});

		buttonsComp.add(addPubButton);

		final JComponent testFrame = new JLabel(
				"Please replace me. I am only a test Publication.");
		// TODO replace label with table

		setLayout(new GridLayout(2, 1));

		add(testFrame);
		add(buttonsComp);
	}

}
