package gui;

import gui.AdvancedGUI.event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import java.awt.Panel;
import java.awt.FlowLayout;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		
		/** Panel that will hold all the components on the JFrame */
		JPanel windowPanel = new JPanel();
		
		
		/** Colour Sliders here */
		/** Add the ChangeListener to the sliders*/
		event e = new event();
		
		/** Create the panel and change the initial colour to black */
		windowPanel.setLayout(null);
		colorPanel = new JPanel();
		colorPanel.setBounds(216, 6, 74, 73);
		colorPanel.setBackground(Color.black);
		
		/** Create a slider panel which will hold the different sliders */
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		sliderPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		sliderPanel.setBounds(10, 122, 300, 120);
		sliderPanel.setLayout(null);
		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		redSlider.addChangeListener(e);
		blueSlider.addChangeListener(e);
		greenSlider.addChangeListener(e);
		JPanel sliders	= new JPanel();
		sliders.setBounds(10, 6, 126, 73);
		sliderPanel.add(sliders);
		
		sliders.setLayout(new GridLayout(3, 1, 3, 3));
		sliders.add(redSlider);
		sliders.add(blueSlider);
		sliders.add(greenSlider);
		sliders.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{redSlider, blueSlider, greenSlider}));
		sliderPanel.add(colorPanel);

		windowPanel.add(sliderPanel);
		
		redLabel = new JLabel(" Red : 0 ");
		blueLabel = new JLabel(" Green : 0 ");
		greenLabel = new JLabel(" Blue : 0 ");
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(146, 6, 60, 73);
		sliderPanel.add(labelPanel);
		
		labelPanel.setLayout(new GridLayout(3, 1, 2, 2));
		labelPanel.add(redLabel);
		labelPanel.add(blueLabel);
		labelPanel.add(greenLabel);
		
		
		/** Create the checkbox which if checked disables the sliders */
		JCheckBox chckbxNewCheckBox = new JCheckBox("Check this box to use random Colours");
		chckbxNewCheckBox.setBounds(10, 86, 231, 23);
		sliderPanel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setSelected(true);
		windowFrame.getContentPane().add(windowPanel);
		JPanel generatePanel = new JPanel();
		generatePanel.setBounds(10, 11, 300, 74);
		generatePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		windowPanel.add(generatePanel);
		generatePanel.setLayout(null);
		JLabel GenerateLabel = new JLabel(" Click Generate to implement your changes! ");
		GenerateLabel.setBounds(50, 49, 211, 14);
		generatePanel.add(GenerateLabel);
		
		
		
		JButton Generate = new JButton("GENERATE");
		Generate.setBounds(91, 11, 130, 31);
		generatePanel.add(Generate);
		Generate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Generate.setToolTipText("Click here to generate a biomorph");
		
		JPanel BiomorphValueChanger = new JPanel();
		BiomorphValueChanger.setBorder(new EmptyBorder(15, 15, 15, 15));
		BiomorphValueChanger.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		BiomorphValueChanger.setBounds(10, 90, 300, 26);
		windowPanel.add(BiomorphValueChanger);
		BiomorphValueChanger.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel ValueLabel = new JLabel("Change the colour of the biomorphs by moving the Slider");
		BiomorphValueChanger.add(ValueLabel);
		BiomorphValueChanger.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{ValueLabel}));
		
		JButton save = new JButton(" Save ");
		save.setBounds(10, 10, 63, 23);
		save.setToolTipText("Save your current biomorph mutation to your local drive");
		JButton load = new JButton(" Load ");
		load.setBounds(83, 10, 61, 23);
		load.setToolTipText("Open up a previously saved biomorph mutation");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		buttonPanel.setBounds(10, 528, 300, 74);
		windowPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		buttonPanel.add(save);
		buttonPanel.add(load);
		
		JButton help1 = new JButton(" Help ");
		help1.setBounds(154, 10, 59, 23);
		buttonPanel.add(help1);
		help1.setToolTipText("Click here for Instructions on how to use the application");
		
		JButton end = new JButton(" Exit ");
		end.setBounds(223, 10, 57, 23);
		buttonPanel.add(end);
		end.setToolTipText("Quit the application by clicking here");
		
		JButton userSelect = new JButton(" Return to User selection ");
		userSelect.setBounds(10, 40, 155, 23);
		buttonPanel.add(userSelect);
		userSelect.setToolTipText("Return to the user selection screen");
		
		JButton HallofFamebutton = new JButton("Hall of Fame");
		HallofFamebutton.setHorizontalAlignment(SwingConstants.LEFT);
		HallofFamebutton.setToolTipText("Insert text here :O");
		HallofFamebutton.setBounds(189, 40, 91, 23);
		buttonPanel.add(HallofFamebutton);
		
		
		
		windowFrame.getContentPane().add(windowPanel);
		
		JPanel bigBiomorphPanel = new JPanel();
		bigBiomorphPanel.setBounds(320, 11, 882, 591);
		windowPanel.add(bigBiomorphPanel);
		bigBiomorphPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		bigBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		/** Create a panel to add the current biomorph */
		JPanel currentBiomorphPanel = new JPanel();
		currentBiomorphPanel.setBounds(10, 285, 300, 232);
		windowPanel.add(currentBiomorphPanel);
		currentBiomorphPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		currentBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		currentBiomorphPanel.setBackground(Color.white);
		
		JPanel CurrentBiormorphPanel = new JPanel();
		CurrentBiormorphPanel.setBounds(10, 248, 300, 26);
		windowPanel.add(CurrentBiormorphPanel);
		CurrentBiormorphPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel CurrentBiomorphLabel = new JLabel("Current Biomorph :");
		CurrentBiormorphPanel.add(CurrentBiomorphLabel);
		
		
		
		
		
		
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
		
		
		
		
		
		
		
		
	}

	public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red : " + r);
			blueLabel.setText("Blue : " + b);
			greenLabel.setText("Green : " + g);
			
			colorPanel.setBackground(new Color(r,g,b));
			
		}
		
	public class event implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red : " + r);
			blueLabel.setText("Blue : " + b);
			greenLabel.setText("Green : " + g);
			
			colorPanel.setBackground(new Color(r,g,b));
			
		}
	}
}
				
		
	


