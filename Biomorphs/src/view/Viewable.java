package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import model.AbstractBiomorph;

public interface Viewable {
	
	void addGenerateListener(ActionListener listener);
	void addMutateListener(ActionListener listener);
	void addUpdateBiomorphListener(MouseListener listener);
	void addExitListener(ActionListener listener);
	void addSaveListener(ActionListener listener);
	void addLoadListener(ActionListener listener);
	void addHelpListener(ActionListener listener);
	
	void updateMutations(List<AbstractBiomorph> biomorphs);
	AbstractBiomorph getMutatedBiomorph();
	
	void exitApplication();
}