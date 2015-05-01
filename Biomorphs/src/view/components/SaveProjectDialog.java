package view.components;

import java.io.IOException;

import javax.swing.JOptionPane;

import model.FileSerializer;

public class SaveProjectDialog<T> extends FileSelectionDialog<T> {

	public SaveProjectDialog(T element) {
		super(element);
	}

	@Override
	protected void processFile(T element, String path) {
		try {			
			new FileSerializer<T>().serialiseFile(element, path);
		} catch (IOException e) {			
			String message = "Unable to save biomorph as a project file.\n\n "
					+ "Please ensure you have permission to read/write to this directory, "
					+ "and that there is enough disk space to complete the operation.";
			
			JOptionPane.showMessageDialog(frame, message, "Unable to save", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
