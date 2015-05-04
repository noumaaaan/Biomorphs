package view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import model.FileSerializer;

/**
 * Load the project into the application 
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public class LoadFile<T> {

	private JFileChooser fileChooser;
	private JButton openButton;

	public LoadFile(){

		openButton = new JButton();

		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory	(new java.io.File("."));

		fileChooser.setDialogTitle("Open your existing Biomorph Project");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	}	

	public T loadFile() throws ClassNotFoundException, IOException {
		T file = null;
		
		if (fileChooser.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION){
			FileSerializer<T> deserialiser = new FileSerializer<T>();
					
			file = deserialiser.deserialiseFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
		return file;
	}
}


