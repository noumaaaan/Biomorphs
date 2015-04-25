package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import biomorph.AbstractBiomorph;
import biomorph.RandomBiomorph;

/**
 * Provides the advanced GUI
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 * 
 */

public class AdvancedGUI extends AbstractGUI{
	
	public AdvancedGUI(){

		/** Frame settings */ 
		super("Biomorph Mutation: Advanced User", 1050, 650);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
		windowFrame.setResizable(false);
		
		/** Create the slider(s) */
		JSlider colourSlider = new JSlider();
		JLabel colourLabel = new JLabel();
		
		
		
		/** Panel to hold the components */
		JPanel slider_panel = new JPanel();
		JPanel button_panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel1.add(slider_panel, BorderLayout.NORTH);
		panel1.add(button_panel, BorderLayout.SOUTH);
		
		windowFrame.add(panel1, BorderLayout.EAST);

		/** Create the components */ 
		JButton test = new JButton(" Generate Biomorph");
		JLabel label_test = new JLabel(" Colour ");
		test.setToolTipText("Testing"); 
		button_panel.add(test);
		button_panel.add(label_test);
		
		
		
		colourSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
		colourSlider.setMajorTickSpacing(5);
		colourSlider.setPaintTicks(true);
		panel1.add(colourSlider);
		
			
		
		/** Defining the draw canvas */
		AbstractBiomorph bio = new RandomBiomorph();
		final BiomorphPanel panel = new BiomorphPanel(bio, true);

		windowFrame.add(panel);
		windowFrame.setVisible(true);
	
		

		
	}
}

