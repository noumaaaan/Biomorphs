package gui;

import gui.AdvancedGUI.event;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Provides the advanced GUI
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 * 
 */

public class AdvancedGUI extends AbstractGUI{
	
	/** Slider Variables */
	JSlider redSlider;
	JSlider blueSlider;
	JSlider greenSlider;
	JLabel redLabel;
	JLabel blueLabel;
	JLabel greenLabel;
	JPanel colorPanel;
	
	public AdvancedGUI(){

		/** Frame settings */ 
		super("Biomorph Mutation: Advanced User", 1250, 650);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
		windowFrame.setResizable(false);
		windowFrame.setVisible(true);

		
		
		/** 1. First Panel (Generate button & label) */
		

		/** Create the different buttons */
		JButton Generate = new JButton("Generate ");
		Generate.setToolTipText("Click here to generate a biomorph");
		JLabel GenerateLabel = new JLabel(" Click Generate to implement your changes! ");
		
		/** Add buttons to the panel */
		JPanel generatePanel = new JPanel();
		generatePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		generatePanel.add(Generate);
		generatePanel.add(GenerateLabel);
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		/** Add the panel to a bigger panel*/
		JPanel windowPanel = new JPanel();
		windowPanel.add(generatePanel, BorderLayout.EAST);
		
		
		
		
	
		
		/** 2. Second Panel (Choose colour) */
		
		
		/** Create the different sliders and their labels*/
		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		
		redLabel = new JLabel(" Red : 0 ");
		blueLabel = new JLabel(" Green : 0 ");
		greenLabel = new JLabel(" Blue : 0 ");
		
		/** Add the ChangeListener to the sliders*/
		event e = new event();
		redSlider.addChangeListener(e);
		blueSlider.addChangeListener(e);
		greenSlider.addChangeListener(e);
		
		/** Create the panel and change the initial colour to black */
		colorPanel = new JPanel();
		colorPanel.setBackground(Color.black);
		
		/** Create a slider panel which will hold the different sliders */
		JPanel sliderPanel = new JPanel();
		
		/** Create the label for the slider and add it to the panel*/
		JLabel sliderLabel = new JLabel("Change the values by using the Slider below : ");
		sliderPanel.add(sliderLabel);
		
		sliderPanel.setLayout(new GridLayout(1, 3, 3, 3));
		JPanel sliders	= new JPanel();
		JPanel labelPanel = new JPanel();
		
		sliderPanel.add(sliders, BorderLayout.NORTH);
		sliderPanel.add(labelPanel, BorderLayout.CENTER);
		sliderPanel.add(colorPanel, BorderLayout.SOUTH);

		
	
		sliders.setLayout(new GridLayout(3, 1, 3, 3));
		sliders.add(redSlider);
		sliders.add(blueSlider);
		sliders.add(greenSlider);
		
		labelPanel.setLayout(new GridLayout(3, 1, 2, 2));
		labelPanel.add(redLabel);
		labelPanel.add(blueLabel);
		labelPanel.add(greenLabel);

		windowPanel.add(sliderPanel);
		windowFrame.add(windowPanel);
		
	}

	public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red " + r);
			blueLabel.setText("Blue " + b);
			greenLabel.setText("Green " + g);
			
			colorPanel.setBackground(new Color(r,g,b));
			
		}
		
	public class event implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red " + r);
			blueLabel.setText("Blue " + b);
			greenLabel.setText("Green " + g);
			
			colorPanel.setBackground(new Color(r,g,b));
			
		}
	}
	
	
}
				
		
	


