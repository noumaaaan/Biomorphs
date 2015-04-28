package view;

import java.awt.event.ActionListener;

public interface Viewable {
	
	void addMutateListener(ActionListener listener);
	void exitListener(ActionListener listener);
	void addSaveListener(ActionListener listener);
	void addLoadListener(ActionListener listener);
	void addHelpListener(ActionListener listener);
	
}
