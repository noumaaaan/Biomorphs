package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AbstractBiomorph;

/**
 * Provides the advanced Graphical User Interface which let's the user manipulate the biomorph
 * 
 * 	1. Create the JFrame
 * 	2. Create the Generate button, label and panel
 * 	3. Create the label for the Sliders and add it to a Panel
 * 	4. Create the different Sliders, labels, Listeners, CheckBox
 * 	5. Create a Panel that will display the Current Biomorph
 * 	6. Create a Panel that displays the different Evolutionary Biomorphs
 * 	7. Create navigation buttons at the bottom of the Panel
 * 	8. Create a Panel that will hold all the components on the Frame
 * 
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 * 
 */

public class AdvancedGUI extends AbstractGUI {
	
	private AbstractBiomorph model;
	
	private BiomorphPanel currentBiomorphPanel;
	private BiomorphGrid biomorphGrid;
	
	private JSlider redSlider;
	private JSlider blueSlider;
	private JSlider greenSlider;
	private JLabel redLabel;
	private JLabel blueLabel;
	private JLabel greenLabel;
	private JPanel colorPanel;
	
	private JButton generateBtn;
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton exitBtn;
	private JButton helpBtn;
	private JButton userSelect;
	
	private JButton saveasfile;
	private JButton saveasproject;
	private JButton cancel;
	private JPanel switchPanel;
	private JCheckBox randomColor;
	
	CardLayout c = new CardLayout();
	
	public AdvancedGUI(AbstractBiomorph model) {
		

		/** 1. Create the JFrame */ 
		
		super("Biomorph Mutation: Advanced User", 1225, 640);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); 
		windowFrame.setResizable(false);
		windowFrame.setVisible(true);
		
		this.model = model;

		
		/** 2. Create the Generate button, label and panel */
		
		/** Button : */
		generateBtn = new JButton("GENERATE");
		generateBtn.setBounds(91, 11, 130, 31);
		generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateBtn.setToolTipText("Click here to generate the biomorph");
		
		/** Label */
		JLabel GenerateLabel = new JLabel(" Click Generate to implement your changes! ");
		GenerateLabel.setBounds(50, 49, 211, 14);	
		
		/** Panel : */
		JPanel generatePanel = new JPanel();
		generatePanel.setBounds(10, 11, 300, 74);
		generatePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		generatePanel.setLayout(null);
		
		/** Add the button and label to the Panel */
		generatePanel.add(generateBtn);
		generatePanel.add(GenerateLabel);
	
		
		
		
		
		/** 3. Create the label for the Sliders and add it to a Panel */
		
		/** Create the Panel */
		JPanel BiomorphValueChanger = new JPanel();
		BiomorphValueChanger.setBorder(new EmptyBorder(15, 15, 15, 15));
		BiomorphValueChanger.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		BiomorphValueChanger.setBounds(10, 90, 300, 26);
		BiomorphValueChanger.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/** Create the label and add it to the Panel */
		JLabel ValueLabel = new JLabel("Change the colour of the biomorphs by moving the Slider");
		BiomorphValueChanger.add(ValueLabel);
		
		
		
		
		
		/** 4. Create the different Sliders, labels, Listeners, CheckBox */
		
		/** Create the different sliders */
		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		redSlider.setMinorTickSpacing(34);
	    redSlider.setPaintTicks(true);
		
	    blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider.setMinorTickSpacing(34);
	    blueSlider.setPaintTicks(true);
		
	    greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider.setMinorTickSpacing(34);
	    greenSlider.setPaintTicks(true); 
	     
		/** Create the different Labels for the Sliders */
		redLabel = new JLabel(" Red : 0 ");
		blueLabel = new JLabel(" Green : 0 ");
		greenLabel = new JLabel(" Blue : 0 ");
		
		/** Create the Panel where the colour will be displayed */
		colorPanel = new JPanel();
		colorPanel.setBounds(216, 6, 74, 73);
		colorPanel.setBackground(Color.black);
		
		/** Create a Panel to hold the Labels */
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(146, 6, 60, 73);
		labelPanel.setLayout(new GridLayout(3, 1, 2, 2));
		labelPanel.add(redLabel);
		labelPanel.add(blueLabel);
		labelPanel.add(greenLabel);
		
		/** Create a Panel to hold the Sliders */
		JPanel sliders	= new JPanel();
		sliders.setBounds(10, 6, 126, 73);
		sliders.setLayout(new GridLayout(3, 1, 3, 3));
		sliders.add(redSlider);
		sliders.add(blueSlider);
		sliders.add(greenSlider);
		
		/** Change Listeners for the Sliders */
		event e = new event();
		redSlider.addChangeListener(e);
		blueSlider.addChangeListener(e);
		greenSlider.addChangeListener(e);
		
		
		/** Create a CheckBox which on default it checked */
		randomColor = new JCheckBox("Use Random Colours");
		randomColor.addItemListener(new randomColor());
		randomColor.setBounds(10, 86, 150, 23);
		randomColor.setSelected(true);
		
		/** Create the Panel that will hold all of the the Slider components*/
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		sliderPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		sliderPanel.setBounds(10, 122, 300, 120);
		sliderPanel.setLayout(null);
		
		/** Create a button to save the colour combination */
		JButton updateColor = new JButton("Update");
		updateColor.setBounds(220, 86, 70, 20);
		updateColor.setToolTipText("Save your current biomorph mutation to your local drive");
		
		
		
		/** Add the different components to the sliderPanel() */
		sliderPanel.add(sliders);
		sliderPanel.add(colorPanel);
		sliderPanel.add(labelPanel);
		sliderPanel.add(randomColor);
		sliderPanel.add(updateColor);
		
		
		
		
		
		/** 5. Create a Panel that will display the Current Biomorph */
		
		/** Create a Label above the Current Biomorph and a Panel to hold it */
		JLabel CurrentBiomorphLabel = new JLabel("Current Biomorph :");
		JPanel CBholdPanel = new JPanel();
		CBholdPanel.setBounds(10, 248, 300, 26);
		CBholdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CBholdPanel.add(CurrentBiomorphLabel);
		
		/** Add the Biomorph to the Panel */
		currentBiomorphPanel = new BiomorphPanel(model, true);
		//panel.setSize(882, 591);
		//currentBiomorphPanel.add(panel);
		currentBiomorphPanel.setLayout(null);
		currentBiomorphPanel.setBounds(10, 285, 300, 232);
		currentBiomorphPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		currentBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		
		/** 6. Create a Panel that displays the different Evolutionary Biomorphs */
		biomorphGrid = new BiomorphGrid(3,3);
		biomorphGrid.setBounds(320, 11, 882, 591);
		biomorphGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		
		/** 7. Create navigation buttons at the bottom of the Panel */
		
		/** Create panel #1 that will hold the different Buttons */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		buttonPanel.setBounds(10, 528, 300, 74);
		buttonPanel.setLayout(null);
		
		
		/** Create Panel #2 that will have the save buttons */
		JPanel savePanel = new JPanel();
		savePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		savePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		savePanel.setBounds(10, 528, 300, 74);
		savePanel.setLayout(null);
	
		/** Different components to add to Button Panel */
		saveBtn = new JButton(" Save ");
		saveBtn.setBounds(10, 10, 63, 23);
		saveBtn.setToolTipText("Save your current biomorph mutation to your local drive");
			
		loadBtn = new JButton(" Load ");
		loadBtn.setBounds(83, 10, 61, 23);
		loadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		helpBtn = new JButton(" Help ");
		helpBtn.setBounds(154, 10, 59, 23);
		helpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		exitBtn = new JButton(" Exit ");
		exitBtn.setBounds(223, 10, 57, 23);
		exitBtn.setToolTipText("Quit the application by clicking here");
		
		userSelect = new JButton(" Return to User selection ");
		userSelect.setBounds(10, 40, 155, 23);
		userSelect.setToolTipText("Return to the user selection screen");
		
		JButton HallofFamebutton = new JButton(" Hall of Fame ");
		HallofFamebutton.setToolTipText("Insert text here :O");
		HallofFamebutton.setBounds(180, 40, 100, 23);
		
		/** TODO Hall of Fame Listener*/ 
		
		
		/** Add these different components to the Panel */
		buttonPanel.add(saveBtn);
		buttonPanel.add(loadBtn);
		buttonPanel.add(helpBtn);
		buttonPanel.add(exitBtn);
		buttonPanel.add(userSelect);
		buttonPanel.add(HallofFamebutton);
		
		
		/** Different components to add to save options Panel */
		saveasfile = new JButton(" Save as JPEG ");
		saveasfile.setBounds(10, 10, 110, 23);
		saveasfile.setToolTipText("Save the Biomorph as an image to your disk");
			
		saveasproject = new JButton(" Save as Project ");
		saveasproject.setBounds(10, 40, 115, 23);
		saveasproject.setToolTipText("Save the project so you can return to it later");
		
		cancel = new JButton(" Cancel ");
		cancel.setBounds(220, 10, 70, 23);
		cancel.setToolTipText("Return to the biomorph project");
		
		/** Add these different components to the Panel */
		savePanel.add(saveasfile);
		savePanel.add(saveasproject);
		savePanel.add(cancel);
		
		/** Create the panel that will hold the other panels*/
		switchPanel = new JPanel();
		switchPanel.setLayout(c);
		
		switchPanel.add(buttonPanel, "card 1");
		switchPanel.add(savePanel, "card 2");
		
		c.show(switchPanel, "card 1");
		
		
		switchPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		switchPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		switchPanel.setBounds(10, 528, 300, 74);
		
		
		
		
		saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				c.show(switchPanel, "card 2");
			
				
			
			
			}});
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				c.show(switchPanel, "card 1");
				
			
			
			}});
		
		
		/** 8. Create a Panel that will hold all the components on the Frame */
		JPanel windowPanel = new JPanel();
		windowFrame.getContentPane().add(windowPanel);
		windowPanel.setLayout(null);
		windowPanel.add(sliderPanel);
		windowPanel.add(generatePanel);
		windowPanel.add(BiomorphValueChanger);
		windowPanel.add(biomorphGrid);
		windowPanel.add(currentBiomorphPanel);
		windowPanel.add(CBholdPanel);
		windowPanel.add(switchPanel);
		
		windowFrame.setVisible(true);
		windowFrame.pack();
		
		addPropertyChangeListeners();
		
	}
	
	
	
	
	private void addPropertyChangeListeners() {
		model.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				if (event.getPropertyName().equals("genome")) {
					AdvancedGUI.this.currentBiomorphPanel.refresh();
					AdvancedGUI.this.biomorphGrid.refresh();
				}
				
			}
		});
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
	
	
	public class randomColor implements ItemListener {
		@SuppressWarnings("deprecation")
		public void itemStateChanged(ItemEvent ie) {
			if (randomColor.isSelected()) {
				
				redSlider.setValue(0);
				redSlider.disable();
				blueSlider.disable();
				blueSlider.setValue(0);
				greenSlider.disable();
				greenSlider.setValue(0);
			
			} else {
				
				redSlider.enable();
				blueSlider.enable();
				greenSlider.enable();
			
			};}}





	
	
	
	
	
	
	
		
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
	
	
	@Override
	public void addMutateListener(ActionListener listener) {
		generateBtn.addActionListener(listener);
	}

	@Override
	public void addExitListener(ActionListener listener) {
		exitBtn.addActionListener(listener);
	}

	@Override
	public void addHelpListener(ActionListener listener) {
		helpBtn.addActionListener(listener);
	}

	@Override
	public void addUpdateBiomorphListener(MouseListener listener) {
		biomorphGrid.addUpdateBiomorphListener(listener);
	}

	@Override
	public void addGenerateListener(ActionListener listener) {
		generateBtn.addActionListener(listener);
	}

	@Override
	public AbstractBiomorph getMutatedBiomorph() {
		return biomorphGrid.getSelectedBiomorph();
	}
	
	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) {
		biomorphGrid.updateGrid(biomorphs);
	}

	@Override
	public void addSaveProjectListener(ActionListener listener) {
		saveasproject.addActionListener(listener);
	}

	@Override
	public void addSaveImageListener(ActionListener listener) {
		saveasfile.addActionListener(listener);
	}
	
	@Override
	public void addLoadProjectListener(ActionListener listener) {
		loadBtn.addActionListener(listener);
	}
	
}