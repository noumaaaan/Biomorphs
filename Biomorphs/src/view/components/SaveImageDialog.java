package view.components;


public class SaveImageDialog<T> extends FileSelectionDialog<T> {

	public SaveImageDialog(T element) {
		super(element);
	}

	@Override
	protected void processFile(T element, String path) {
		// TODO save as image here
	}
	
}
