package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AbstractBiomorph;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 */
public class BasicGUI extends AbstractGUI implements BiomorphInterface {
	
	private JButton newBiomorphBtn;
	private JButton newSaveBtn;
	private JButton newLoadBtn;
	private JButton newHelpBtn;
	private JButton newEndBtn;
	private JButton saveasjpeg;
	
	private BiomorphPanel panel;

	public BasicGUI(AbstractBiomorph model) {
		super("Biomorph Mutation: Beginner User", 800, 600);

		/** Panel to hold the components */
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(7, 1, 20, 20));

		/** Create the buttons */ 
		newBiomorphBtn = new JButton(" Generate Biomorph ");
		newBiomorphBtn.setToolTipText("Create a new biomorph by clicking here"); 
		
		newSaveBtn = new JButton(" Save as Project ");
		newSaveBtn.setToolTipText("Save your project to return to it later");
		
		saveasjpeg = new JButton(" Save as JPEG ");
		saveasjpeg.setToolTipText("Save your current biomorph mutation to your local drive");
		
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
		button_panel.add(saveasjpeg);
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

	@Override
	public void addGenerateListener(ActionListener listener) {
		newBiomorphBtn.addActionListener(listener);
	}

	public void addMutateListener(ActionListener listener) {
		newBiomorphBtn.addActionListener(listener);
	}
	
	public void addExitListener(ActionListener listener) {
		newEndBtn.addActionListener(listener);
	}
	
	public void addSaveProjectListener(ActionListener listener) {
		newSaveBtn.addActionListener(listener);
	}
	
	public void addLoadProjectListener(ActionListener listener) {
		newLoadBtn.addActionListener(listener);
	}
	
	public void addHelpListener(ActionListener listener) {
		newHelpBtn.addActionListener(listener);
	}

	@Override
	public void addUpdateBiomorphListener(MouseListener listener) { } // not needed for this gui

	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) { }

	@Override
	public AbstractBiomorph getMutatedBiomorph() { return null; }

	@Override
	public void addSaveImageListener(ActionListener listener) { }

	@Override
	public JFrame getFrame() {
		return windowFrame;
	}

	@Override
	public void addHallOfFameViewListener(ActionListener listener) { } // not on this gui

	@Override
	public void addHallOfFameAddListener(ActionListener listener) { } // not on this gui

	@Override
	public void addGenomeChangeListener(ActionListener listener) { }

	@Override
	public GenomeViewUpdateModel getGenomeUpdate() {
		return null;
	}

	@Override
	public void addDeleteHallOfFameBiomorph(ActionListener listener) { } // not on this gui

	@Override
	public void addLoadHallOfFameBiomorph(ActionListener listener) { } // not on this gui

	@Override
	public BiomorphPanel getBiomorphPanel() {
		return panel;
	}

	@Override
	public void addUndoListener(ActionListener listener) { } // not on this gui
	
	@Override
	public void loadHallOfFame() { } // not on this gui
}