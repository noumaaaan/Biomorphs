package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AbstractBiomorph;
import controller.EventAction;

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
	private JButton userSelect;
	
	private BiomorphPanel panel;

	public BasicGUI(AbstractBiomorph model) {
		super("Biomorph Mutation: Beginner User", 1500, 640);

		/** Panel to hold the components */
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(7, 1, 20, 20));

		/** Create the buttons */ 
		newBiomorphBtn = new JButton(" Generate Biomorph ");
		newBiomorphBtn.setToolTipText("Create a new biomorph by clicking here"); 
		
		newSaveBtn = new JButton(" Save as Project ");
		newSaveBtn.setToolTipText("Save your project to return to it later");
		
		saveasjpeg = new JButton(" Save as Image ");
		saveasjpeg.setToolTipText("Save your current biomorph mutation to your local drive");
		
		newLoadBtn = new JButton(" Load ");
		newLoadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		newHelpBtn = new JButton(" Help ");
		newHelpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		userSelect = new JButton(" Return to User selection ");
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
		this.panel = new BiomorphPanel(model, true, true);	
		
		/** TODO 9. Hall of fame Panel */
		JPanel hofPanel = new JPanel();
		hofPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		hofPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		hofPanel.setBounds(1205, 10, 270, 590);
		hofPanel.setLayout(null);
		
		JLabel hofLabel = new JLabel(" Hall of Fame ");
		hofLabel.setBounds(100, 10, 150, 14);	
		hofLabel.setFont((new Font("Tahoma", Font.PLAIN, 16)));
		
		
		JPanel hof1 = new JPanel();
		hof1.setBorder(new EmptyBorder(15, 15, 15, 15));
		hof1.setBorder(BorderFactory.createLineBorder(Color.red));
		hof1.setBounds(10, 35, 250, 150);
		hof1.setLayout(null);
		
		JPanel hof2 = new JPanel();
		hof2.setBorder(new EmptyBorder(15, 15, 15, 15));
		hof2.setBorder(BorderFactory.createLineBorder(Color.green));
		hof2.setBounds(10, 220, 250, 150);
		hof2.setLayout(null);
		
		JPanel hof3 = new JPanel();
		hof3.setBorder(new EmptyBorder(15, 15, 15, 15));
		hof3.setBorder(BorderFactory.createLineBorder(Color.blue));
		hof3.setBounds(10, 405, 250, 150);
		hof3.setLayout(null);
		
		JButton hof1remove = new JButton("Remove");
		hof1remove.setFont((new Font("Tahoma", Font.PLAIN, 12)));
		hof1remove.setBounds(10, 190, 90, 20);
		
		JButton hof2remove = new JButton("Remove");
		hof2remove.setFont((new Font("Tahoma", Font.PLAIN, 12)));
		hof2remove.setBounds(10, 375, 90, 20);
		
		
		JButton hof3remove = new JButton("Remove");
		hof3remove.setFont((new Font("Tahoma", Font.PLAIN, 12)));
		hof3remove.setBounds(10, 560, 90, 20);
		
		
		hofPanel.add(hofLabel);
		hofPanel.add(hof1remove);
		hofPanel.add(hof2remove);
		hofPanel.add(hof3remove);
		hofPanel.add(hof1);
		hofPanel.add(hof2);
		hofPanel.add(hof3);
		
		
		
		
		
		
		
		
		
		
		
		
		
		windowFrame.add(panel);
		windowFrame.setVisible(true);
		windowFrame.add(hofPanel);

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
	public void addGenerateListener(EventAction listener) {
		newBiomorphBtn.addActionListener(listener);
	}

	public void addMutateListener(EventAction listener) {
		newBiomorphBtn.addActionListener(listener);
	}
	
	public void addExitListener(EventAction listener) {
		newEndBtn.addActionListener(listener);
	}
	
	public void addSaveProjectListener(EventAction listener) {
		newSaveBtn.addActionListener(listener);
	}
	
	public void addLoadProjectListener(EventAction listener) {
		newLoadBtn.addActionListener(listener);
	}
	
	public void addHelpListener(EventAction listener) {
		newHelpBtn.addActionListener(listener);
	}

	@Override
	public void addUpdateBiomorphListener(EventAction listener) { } // not needed for this gui

	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) { }

	@Override
	public AbstractBiomorph getMutatedBiomorph() { return null; }

	@Override
	public void addSaveImageListener(EventAction listener) { 
		saveasjpeg.addActionListener(listener);
	}

	@Override
	public JFrame getFrame() {
		return windowFrame;
	}

	@Override
	public void addAddHallOfFameListener(EventAction listener) { } // not on this gui

	@Override
	public void addGenomeChangeListener(EventAction listener) { }

	@Override
	public GenomeViewUpdateModel getGenomeUpdate() {
		return null;
	}

	@Override
	public void addDeleteHallOfFameBiomorphListener(EventAction listener) { } // not on this gui

	@Override
	public void addLoadHallOfFameBiomorphListener(EventAction listener) { } // not on this gui

	@Override
	public BiomorphPanel getBiomorphPanel() {
		return panel;
	}

	@Override
	public void addUndoListener(EventAction listener) { } // not on this gui
	
	@Override
	public void loadHallOfFame(List<AbstractBiomorph> biomorphs) {}

	@Override
	public AbstractBiomorph getHofBiomorphToDelete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AbstractBiomorph getHofBiomorphToLoad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addManipulateBiomorphListener(MouseListener listener) {
		panel.addMouseListener(listener);
	}

	@Override
	public void addLoadInterfacePickerListener(EventAction listener) {
		userSelect.addActionListener(listener);
	}
}