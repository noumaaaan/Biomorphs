package view;

import java.awt.event.ActionListener;

public interface Viewable {
	
	void addMutateListener(ActionListener listener);
	void addExitListener(ActionListener listener);
	void addSaveListener(ActionListener listener);
	void addLoadListener(ActionListener listener);
	void addHelpListener(ActionListener listener);
	
	
	
}
