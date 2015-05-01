package view.components;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Save the project
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public abstract class FileSelectionDialog<T> {
	JFrame frame; 
	JFileChooser fileChooser = new JFileChooser();

	private T element;

	public FileSelectionDialog(T element) {
		// parent component of the dialog
		frame = new JFrame();
		fileChooser.setDialogTitle("Save the project");
		fileChooser = new JFileChooser();  
		this.element=element; 
	}


	public void saveFile() throws IOException{
		int userSelection = fileChooser.showSaveDialog(frame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			processFile(element, fileToSave.getAbsolutePath());
		}
	}
	
	protected abstract void processFile(T element, String path);
}