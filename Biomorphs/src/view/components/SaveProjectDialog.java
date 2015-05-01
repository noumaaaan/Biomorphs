package view.components;

import java.io.IOException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
