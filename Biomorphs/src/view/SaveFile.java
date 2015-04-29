package view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Save the project
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public class SaveFile {

	public SaveFile() {
		// parent component of the dialog
		JFrame frame = new JFrame(); 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save the project");   
		 
		int userSelection = fileChooser.showSaveDialog(frame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}

	
	//TODO functionality incomplete

}




