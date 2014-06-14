/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DataBaseAuthorTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabaseMenuBar;

/**
 * @author mathias
 * 
 */
public class LiteratureDatabaseView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -30430229188690490L;
	private final JComponent authorComponenet;
	private final JComponent publicationComponent;
	private final JMenuBar menu;

	/**
	 * @throws HeadlessException
	 */
	public LiteratureDatabaseView() {
		authorComponenet = new DataBaseAuthorTable();
		publicationComponent = addPublicationsView();
		menu = new DatabaseMenuBar();
		setLayout(new GridLayout(2, 1));
		this.add(authorComponenet);
		this.add(publicationComponent);
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		validate();
		setVisible(true);
	}

	private JComponent addPublicationsView() {
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

		final JPanel pubComp = new JPanel();

		pubComp.setLayout(new GridLayout(2, 1));
		final JComponent testFrame = new JLabel(
				"Please replace me. I am only a test Publication.");
		// TODO replace label with table
		pubComp.add(testFrame);
		pubComp.add(buttonsComp);
		return pubComp;
	}
	public static void main(final String[] args) {
		new LiteratureDatabaseView();
	}

}
