package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 	1. 		This is the start up screen that the viewer will first see 
 * 			It Displays a splash screen with user selection.
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */
public class GuiStartup extends AbstractGUI {

	public static void main(String[] args) {
		if (args.length == 0) {
			new GuiStartup().displayGui();
		} else {
			switch (args[0]) {
				case "evolutionary":
					new EvolutionaryGUI().displayGui();
					break;
				case "basic":
					new BasicGUI().displayGui();
					break;
				default:
					new GuiStartup().displayGui();
			}
		}
	}

	public GuiStartup() {
		super("Biomorph Mutation: Select User type", 500, 300);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);

		JLabel description1 = new JLabel("Welcome to the Biomorph Mutation Application");
		JLabel description2 = new JLabel("Produce evolutionary art based on Richard Dawkin's Biomorphs in a creative way");
		JLabel usertype = new JLabel("First, Choose the type of user you are: ");
		
		JButton beginner = new JButton("Beginner");
		beginner.setToolTipText("Using the application for the first time? Choose Beginner");
		
		JButton advanced = new JButton("Advanced");
		advanced.setToolTipText("Want to experience something extraordinary? Choose advanced");
		
		JButton quit= new JButton("Quit Application");
		quit.setToolTipText("Exit the Application. Unsaved Settings will be lost");
		
		JPanel panel = new JPanel(new GridBagLayout());

	
		// Add the different components to panel
		windowFrame.getContentPane().add(panel, BorderLayout.NORTH);
		windowFrame.add(panel);	
		panel.add(description1);
		panel.add(description2);
		panel.add(usertype);
		panel.add(beginner);
		panel.add(advanced);
		panel.add(quit);

		// Set GridBag constraints to position the components on the JFrame
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(description1, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(description2, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usertype, c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(beginner, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(advanced, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(quit, c);			
		
		// Add the listener for the beginner setting
		beginner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				new BasicGUI().displayGui();
			}

		});

		// Add the listener for the advanced setting
		advanced.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				new EvolutionaryGUI().displayGui();
			}

		});

		
		//Action listener to close application
				quit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						exitApplication();
					}
				});
		
		
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}
}