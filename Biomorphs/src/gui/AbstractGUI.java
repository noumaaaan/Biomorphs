package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Base class for all GUIs
 * 
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractGUI {
	
	protected JFrame windowFrame;
	
	public AbstractGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // use native appearance
		} catch (Exception e) {
			System.err.println("Failed to set graphics mode. Reverting to Java LAF.");
		}
	}
	
	/**
	 * Hide
	 * @param existingFrame
	 * @param gui
	 */
	protected void destroyGui() {
		windowFrame.setVisible(false);
		windowFrame.dispose();
	}
	
	public void displayGui() {
		windowFrame.setVisible(true);
	}
	
	protected void exitApplication(JFrame frame)
	{
		int response = JOptionPane.showConfirmDialog(frame,
				"Would you really like to quit the application?", 
				"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
