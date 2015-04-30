package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.AbstractBiomorph;
import model.FileSerializer;

/**
 * Save the project
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public class SaveFile<T> {
	JFrame frame; 
	JFileChooser fileChooser = new JFileChooser();

	private T element;

	public SaveFile(T element) {
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
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());

			new FileSerializer<T>().serialiseFile(element,fileToSave.getAbsolutePath());
		}

		//TODO functionality incomplete

	}
}