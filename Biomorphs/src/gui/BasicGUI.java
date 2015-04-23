package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import biomorph.AbstractBiomorph;
import biomorph.RandomBiomorph;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 */
public class BasicGUI extends AbstractGUI {

	public BasicGUI() {
		super("Biomorph Mutation: Beginner User", 800, 600);

		//Create the panel to hold the buttons and define Grid Layout
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(6, 1, 20, 20));

		//Create the buttons for the panel 
		JButton start = new JButton(" Generate Biomorph ");
		start.setToolTipText("Create a new biomorph by clicking here"); 
		
		JButton save = new JButton(" Save ");
		save.setToolTipText("Save your current biomorph mutation to your local drive");
		
		JButton load = new JButton(" Load ");
		load.setToolTipText("Open up a previously saved biomorph mutation");
		
		JButton help1 = new JButton(" Help ");
		help1.setToolTipText("Click here for Instructions on how to use the application");
		
		JButton userSelect = new JButton(" Return to User selection ");
		userSelect.setToolTipText("Return to the user selection screen");
		
		JButton end = new JButton(" Exit ");
		end.setToolTipText("Quit the application by clicking here");

		//Place the buttons within the panel and add them to the frame
		button_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_panel.add(start);
		button_panel.add(save);
		button_panel.add(load);
		button_panel.add(help1);
		button_panel.add(userSelect);
		button_panel.add(end);
		windowFrame.add(button_panel, BorderLayout.WEST);

		//Defining the Draw Canvas
		AbstractBiomorph bio = new RandomBiomorph();
		final BiomorphPanel panel = new BiomorphPanel(bio, true);

		windowFrame.add(panel);
		windowFrame.setVisible(true);
		
		
		//Action listener to Generate a new biomorph
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				panel.getBiomorph().mutate();
				panel.refresh();
			}
		});
		
		
		//Action listener to click the help option
		help1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
				
				helpScreen();	
				} 
				
				catch (FileNotFoundException e) {
				System.out.println("Failed to read the File. Contact Developer");
				}
			}
		});
				
		
		
		// Add the listener to return to User Selection
		userSelect.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						returnUserSelect();
					}});
				
		
		//Action listener to close application
		end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				exitApplication();
			}
		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

}