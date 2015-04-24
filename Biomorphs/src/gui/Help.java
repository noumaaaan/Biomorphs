package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		windowFrame.add(text, BorderLayout.CENTER);		
		
		try {
			   FileReader fr = new FileReader("Files/text.txt");
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
				new BasicGUI().displayGui();
			}

		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}
}