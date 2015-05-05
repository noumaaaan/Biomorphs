package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.AbstractBiomorph;

/**
 * Class to display User Manual 
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * 
 */

public class Help extends AbstractGUI{

	public Help() {
		super("Biomorph Mutation: Help", 600, 400);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);
		windowFrame.setLayout(new BorderLayout());

		JButton close = new JButton("Return");
		close.setToolTipText("Close the help screen and return to the beginner menu");
		JPanel panel = new JPanel();
			
		panel.add(close);
		windowFrame.add(panel, BorderLayout.SOUTH);
		
		JTextArea text= new JTextArea();
		text.setEditable(false);
		text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		windowFrame.add(text, BorderLayout.CENTER);		
		
		try {
			   FileReader fr = new FileReader("Files/helpText.txt");
			   BufferedReader reader = new BufferedReader(fr);
			   text.read(reader, "text");
			   
			}
			   catch (IOException ioe) {
			   System.err.println(ioe);
			   System.exit(1);
			}
		
		
		  JScrollPane scroll = new JScrollPane (text);
		  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  windowFrame.add(scroll);
		
		  /**Return to beginner*/
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
			}

		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	@Override
	public void addMutateListener(ActionListener listener) { }

	@Override
	public void addExitListener(ActionListener listener) { }

	@Override
	public void addSaveProjectListener(ActionListener listener) { }

	@Override
	public void addLoadProjectListener(ActionListener listener) { }

	@Override
	public void addHelpListener(ActionListener listener) { }

	@Override
	public void addGenerateListener(ActionListener listener) { }

	@Override
	public void addUpdateBiomorphListener(MouseListener listener) { }

	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) { }

	@Override
	public AbstractBiomorph getMutatedBiomorph() { return null; }

	@Override
	public void addSaveImageListener(ActionListener listener) { }
}