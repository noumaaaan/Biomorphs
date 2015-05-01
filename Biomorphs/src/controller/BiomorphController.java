package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.AbstractBiomorph;
import model.EvolutionaryBiomorph;
import model.Genome;
import model.RandomBiomorph;
import view.AdvancedGUI;
import view.Help;
import view.Viewable;
import view.components.SaveImageDialog;
import view.components.SaveProjectDialog;

public class BiomorphController {

	private AbstractBiomorph model;
	private Viewable view; 
	
	public BiomorphController(Viewable view, AbstractBiomorph biomorph) {
		this.view = view; 
		this.model = biomorph;
		
		// update the mutations for the current biomorph
		view.updateMutations(getMutatedBiomorphs());
		
		// add listeners to the interface
		view.addMutateListener(new MutateListener());
		view.addExitListener(new ExitListener());
		view.addUpdateBiomorphListener(new UpdateBiomorphListener());
		view.addHelpListener(new HelpListener());
		view.addSaveProjectListener(new SaveProjectListener());
		
	}
	
	/**
	 * Generates a list of mutated biomorphs, based on the genome of the active biomorph.
	 * 
	 * @return List<AbstractBiomorph>
	 */
	private List<AbstractBiomorph> getMutatedBiomorphs() {
		ArrayList<AbstractBiomorph> biomorphs = new ArrayList<AbstractBiomorph>();
		
		for(int i = 0; i < 9; i++) {
			AbstractBiomorph biomorph = new EvolutionaryBiomorph(model.getGenome());
			biomorph.mutate();
			
			biomorphs.add(biomorph);
		}
		
		return biomorphs;
	}
	
	/**
	 * Action to run when the active biomorph is mutated.
	 */
	class MutateListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.mutate();
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	/**
	 * Action to run when exiting the application.
	 */
	class ExitListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.exitApplication();
		}
		
	}
	
	/**
	 * Action to run when an active biomorph has been updated with a new genome.
	 */
	class UpdateBiomorphListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setGenome(view.getMutatedBiomorph().getGenome());
			
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	/**
	 * Action to run when a help button is pressed.
	 */
	class HelpListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Help().displayGui();
		}
		
	}
	
	
	/**
	 * Action to save when a biomorph when save button is pressed.
	 */
	class SaveProjectListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new SaveProjectDialog<Genome>(model.getGenome()).saveFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
		
	}
	
	/**
	 * Action to save when a biomorph when save button is pressed.
	 */
	class SaveImageListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new SaveImageDialog<Genome>(model.getGenome()).saveFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	

	
	/**
	 * Creates an instance of the controller, with a view and model.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		Viewable view = new AdvancedGUI(model);
		
		new BiomorphController(view, model);
	}
	
}