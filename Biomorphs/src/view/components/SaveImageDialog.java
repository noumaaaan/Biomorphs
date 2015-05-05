package view.components;

import javax.swing.JFrame;


public class SaveImageDialog<T> extends SaveFileDialog<T> {

	public SaveImageDialog(JFrame frame, T element) {
		super(frame, element);
	}

	@Override
	protected void processFile(T element, String path) {
		// TODO save as image here
	}
	
}
