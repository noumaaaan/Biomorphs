package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import model.AbstractBiomorph;

public interface BiomorphInterface {
	
	void addGenerateListener(ActionListener listener);
	void addMutateListener(ActionListener listener);
	void addUpdateBiomorphListener(MouseListener listener);
	void addExitListener(ActionListener listener);
	void addHelpListener(ActionListener listener);
	void addUndoListener(ActionListener listener);
	
	void loadHallOfFame();
	void addHallOfFameAddListener(ActionListener listener);
	void addHallOfFameViewListener(ActionListener listener);
	void addDeleteHallOfFameBiomorph(ActionListener listener);
	void addLoadHallOfFameBiomorph(ActionListener listener);
	
	void addLoadProjectListener(ActionListener listener);
	
	void addSaveProjectListener(ActionListener listener);
	void addSaveImageListener(ActionListener listener);	
	
	void addGenomeChangeListener(ActionListener listener);
		
	void updateMutations(List<AbstractBiomorph> biomorphs);
	AbstractBiomorph getMutatedBiomorph();
	
	GenomeViewUpdateModel getGenomeUpdate();
	
	JFrame getFrame();
	
	void exitApplication();
	
	BiomorphPanel getBiomorphPanel();
}