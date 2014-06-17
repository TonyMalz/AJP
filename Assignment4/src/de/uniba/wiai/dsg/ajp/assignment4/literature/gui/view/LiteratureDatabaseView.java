/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.sun.istack.internal.logging.Logger;

import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DataBaseAuthorTable;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabaseMenuBar;
import de.uniba.wiai.dsg.ajp.assignment4.literature.gui.view.components.DatabasePublicationTable;

/**
 * @author mathias
 * 
 */
public class LiteratureDatabaseView extends JFrame {
	private final Logger LOGER = Logger.getLogger(LiteratureDatabaseView.class);
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
		publicationComponent = new DatabasePublicationTable();
		menu = new DatabaseMenuBar();
		setLayout(new GridLayout(2, 1));
		this.add(authorComponenet);
		this.add(publicationComponent);
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		validate();
		setVisible(true);
	}

	public static void main(final String[] args) {
		new LiteratureDatabaseView();
	}

}
