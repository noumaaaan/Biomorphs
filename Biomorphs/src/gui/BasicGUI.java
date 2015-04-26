package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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

		/** Panel to hold the components */
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(6, 1, 20, 20));

		/** Create the buttons */ 
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

		/** Add buttons to the panel */
		button_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_panel.add(start);
		button_panel.add(save);
		button_panel.add(load);
		button_panel.add(help1);
		button_panel.add(userSelect);
		button_panel.add(end);
		windowFrame.add(button_panel, BorderLayout.WEST);

		/** Defining the draw canvas */
		AbstractBiomorph bio = new RandomBiomorph();
		final BiomorphPanel panel = new BiomorphPanel(bio, true);

		windowFrame.add(panel);
		windowFrame.setVisible(true);
		
		
		
		
		
		/** List Action Listeners below : */
		
		
		
		
		/** 1. Generate new Biomorph */
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				panel.getBiomorph().mutate();
				panel.refresh();
				panel.clearActiveLine();
		}});
	
		
		/** 2. Save the Biomorph */
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			new SaveFile();
				
		}});
			
		
		/** 3. Load the Biomorph */
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new LoadFile();
				
		}});
		
		
			
		/** 4. Help Screen */
		help1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				destroyGui();
				new Help().displayGui();
				
			}});
	
				
		/** 5. Return to User Selection */
		userSelect.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						returnUserSelect();
		}});
				
		
		/** 6. Exit the Application */
		end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				exitApplication();
		}});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

}