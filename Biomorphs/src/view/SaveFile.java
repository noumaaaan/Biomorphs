package view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Save the project
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public class SaveFile<T> {
	
	JFrame frame;
	JFileChooser fileChooser;

	public SaveFile() {
		// parent component of the dialog
		frame  = new JFrame(); 
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save the project");   		
	}
	
	public void saveFile(T object) {
		int userSelection = fileChooser.showSaveDialog(frame);
	}

	
	//TODO functionality incomplete

}




