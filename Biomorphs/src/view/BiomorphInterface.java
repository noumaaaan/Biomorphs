package view;

import java.util.List;

import javax.swing.JFrame;

import model.AbstractBiomorph;
import controller.EventAction;

public interface BiomorphInterface {
	
	void addGenerateListener(EventAction listener);
	void addMutateListener(EventAction listener);
	void addUpdateBiomorphListener(EventAction listener);
	void addExitListener(EventAction listener);
	void addHelpListener(EventAction listener);
	void addUndoListener(EventAction listener);
	
	void loadHallOfFame(List<AbstractBiomorph> biomorphs);
	void addHallOfFameAddListener(EventAction listener);
	void addDeleteHallOfFameBiomorph(EventAction listener);
	void addLoadHallOfFameBiomorph(EventAction listener);
	
	int getHofNumberBiomorphToDelete(); // delete of fame biomorph
	AbstractBiomorph getHofBiomorphToLoad(); // load hall of fame bopmorph
	
	void addLoadProjectListener(EventAction listener);
	
	void addSaveProjectListener(EventAction listener);
	void addSaveImageListener(EventAction listener);	
	
	void addGenomeChangeListener(EventAction listener);
		
	void updateMutations(List<AbstractBiomorph> biomorphs);
	AbstractBiomorph getMutatedBiomorph();
	
	GenomeViewUpdateModel getGenomeUpdate();
	
	JFrame getFrame();
	
	void exitApplication();
	
	BiomorphPanel getBiomorphPanel();
}