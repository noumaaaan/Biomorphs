package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
	

	private List<BiomorphPanel> hallOfFameBiomorphs;
	private List<JButton> hallOfFameDeleteButtons;
	private BiomorphPanel biomorphPanelToDelete;
	private BiomorphPanel biomorphPanelToLoad;
	
	private GenomeViewUpdateModel genomeUpdate;
	
	private BiomorphPanel panel;

	public BasicGUI(AbstractBiomorph model) {
		super("Biomorph Mutation: Beginner User", 1200, 640);

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
		
		newLoadBtn = new JButton(" Load Project");
		newLoadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		newHelpBtn = new JButton(" Help ");
		newHelpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		userSelect = new JButton(" Main Menu ");
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
		
		
		
		
		
		
		hallOfFameBiomorphs = new ArrayList<BiomorphPanel>();
		hallOfFameDeleteButtons = new ArrayList<JButton>();
		
		JPanel hofPanel = new JPanel();
		hofPanel.setBounds(919, 0, 270, 600);
		hofPanel.setLayout(null);
		
		JLabel hofLabel = new JLabel(" Hall of Fame ");
		hofLabel.setBounds(80, 0, 150, 40);
		hofLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			
		hofPanel.add(hofLabel);
		
		/* start point to position components at */
		int previousPanelY = 35;
		int previousBtnY = 190;
		
		for(int i = 0; i < 3; i++) {
			BiomorphPanel hofSubpanel = new BiomorphPanel();
			hofSubpanel.setBorder(new EmptyBorder(15, 15, 15, 15));
			hofSubpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			hofSubpanel.setBounds(10, previousPanelY, 250, 150);
			hofSubpanel.setLayout(null);
			hofSubpanel.addMouseListener(new HallOfFameLoadListener(hofSubpanel));
			
			JButton hofRemoveBtn = new JButton("Remove");
			hofRemoveBtn.setFont((new Font("Tahoma", Font.PLAIN, 12)));
			hofRemoveBtn.setBounds(95, previousBtnY, 90, 20);
			hofRemoveBtn.addMouseListener(new HallOfFameDeleteListener(hofSubpanel));
			
			previousPanelY += 185; // height of each component
			previousBtnY += 185;
			
			hofPanel.add(hofSubpanel);
			hofPanel.add(hofRemoveBtn);
			
			hallOfFameBiomorphs.add(hofSubpanel);
			hallOfFameDeleteButtons.add(hofRemoveBtn);
		}				
		
	
		windowFrame.add(hofPanel);
		
		
		
		
		
		
		
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
	public BiomorphPanel getBiomorphPanel() {
		return panel;
	}

	@Override
	public void addUndoListener(EventAction listener) { } // not on this gui
	
	

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
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public GenomeViewUpdateModel getGenomeUpdate() {
		return genomeUpdate;
	}
	
	
	
	@Override
	public void addLoadHallOfFameBiomorphListener(EventAction listener) {
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.addMouseListener(listener);
		}
	}

	@Override
	public void addDeleteHallOfFameBiomorphListener(EventAction listener) {
		for(JButton button : hallOfFameDeleteButtons) {
			button.addMouseListener(listener);
		}
	}

	

	@Override
	public void loadHallOfFame(List<AbstractBiomorph> biomorphs) {
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.setBiomorph(null);
		}
		
		if(biomorphs.size() <= 3) {
			for(int i = 0; i < biomorphs.size(); i++) {
				hallOfFameBiomorphs.get(i).setBiomorph(biomorphs.get(i));
			}
		}
		
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.refresh(); // ensure all panels are painted with new biomorph
		}
	}
	
	class HallOfFameDeleteListener extends EventAction {
		
		private BiomorphPanel biomorphPanel;
		
		public HallOfFameDeleteListener(BiomorphPanel biomorphPanel) {
			this.biomorphPanel = biomorphPanel;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			biomorphPanelToDelete = biomorphPanel;
		}
	}
	
	class HallOfFameLoadListener extends EventAction {
		
		private BiomorphPanel biomorphPanel;
		
		public HallOfFameLoadListener(BiomorphPanel biomorphNumber) {
			this.biomorphPanel = biomorphNumber;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			biomorphPanelToLoad = biomorphPanel;
		}
	}


	
	
	
	
	
	
	
}