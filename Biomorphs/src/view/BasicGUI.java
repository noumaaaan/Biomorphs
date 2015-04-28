package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AbstractBiomorph;
import model.AbstractModel;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 */
public class BasicGUI extends AbstractGUI {
	
	private JButton newBiomorphBtn;
	private JButton newSaveBtn;
	private JButton newLoadBtn;
	private JButton newHelpBtn;
	private JButton newEndBtn;
	private AbstractModel model;
	
	private BiomorphPanel panel;

	public BasicGUI(AbstractBiomorph model) {
		super("Biomorph Mutation: Beginner User", 800, 600);
		
		this.model = model;

		/** Panel to hold the components */
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(6, 1, 20, 20));

		/** Create the buttons */ 
		newBiomorphBtn = new JButton(" Generate Biomorph ");
		newBiomorphBtn.setToolTipText("Create a new biomorph by clicking here"); 
		
		newSaveBtn = new JButton(" Save ");
		newSaveBtn.setToolTipText("Save your current biomorph mutation to your local drive");
		
		newLoadBtn = new JButton(" Load ");
		newLoadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		newHelpBtn = new JButton(" Help ");
		newHelpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		JButton userSelect = new JButton(" Return to User selection ");
		userSelect.setToolTipText("Return to the user selection screen");
		
		newEndBtn = new JButton(" Exit ");
		newEndBtn.setToolTipText("Quit the application by clicking here");

		/** Add buttons to the panel */
		button_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_panel.add(newBiomorphBtn);
		button_panel.add(newSaveBtn);
		button_panel.add(newLoadBtn);
		button_panel.add(newHelpBtn);
		button_panel.add(userSelect);
		button_panel.add(newEndBtn);
		windowFrame.add(button_panel, BorderLayout.WEST);

		/** Defining the draw canvas */
		this.panel = new BiomorphPanel(model, true);

		windowFrame.add(panel);
		windowFrame.setVisible(true);		

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
		
		model.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				if (event.getPropertyName().equals("genome")) {
					BasicGUI.this.panel.refresh();
				}
				
			}
		});
	}

	public void addMutateListener(ActionListener listener) {
		newBiomorphBtn.addActionListener(listener);
	}

	
	public void addExitListener(ActionListener listener) {
		newEndBtn.addActionListener(listener);
	}
	
	public void addSaveListener(ActionListener listener) {
		newSaveBtn.addActionListener(listener);
	}
	
	public void addLoadListener(ActionListener listener) {
		newLoadBtn.addActionListener(listener);
	}
	
	public void addHelpListener(ActionListener listener) {
		newHelpBtn.addActionListener(listener);
	}

	
	
}