package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.LiteratureDatabaseView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.PublicationConfigurationView;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.listener.DataBaseViewKeyListner;
import de.uniba.wiai.dsg.ajp.assignment4.literature.logic.model.Publication;
/**
 * the second half of the main view. consists of the table and two buttons
 * (add/remove).
 * 
 * @author mathias
 * 
 */
public class DatabasePublicationTable extends JPanel {

	/** serial version. */
	private static final long serialVersionUID = -1096171845816582557L;
	/**
	 * Constructor to initate the components.
	 */
	public DatabasePublicationTable() {
		addKeyListener(new DataBaseViewKeyListner());
		final JPanel buttonsComp = new JPanel();
		buttonsComp.addKeyListener(new DataBaseViewKeyListner());
		buttonsComp.setLayout(new GridLayout(1, 2));
		// TODO add publication Table
		final Button deleteSelectedAuthorButton = new Button(
				"Remove selected publication");
		deleteSelectedAuthorButton.addKeyListener(new DataBaseViewKeyListner());
		deleteSelectedAuthorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Publication pubToRemove = null;
				LiteratureDatabaseView.removePublication(pubToRemove);

			}

		});

		buttonsComp.add(deleteSelectedAuthorButton);
		final Button addPubButton = new Button("Create Publication");
		addPubButton.addKeyListener(new DataBaseViewKeyListner());
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
		testFrame.addKeyListener(new DataBaseViewKeyListner());
		setLayout(new GridLayout(2, 1));

		add(testFrame);
		add(buttonsComp);
	}

}
