package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model.AbstractBiomorph;
import model.EvolutionaryBiomorph;
import model.FileSerializer;
import model.Genome;
import model.RandomBiomorph;
import view.AdvancedGUI;
import view.BiomorphInterface;
import view.GenomeViewUpdateModel;
import view.HallOfFameGUI;
import view.Help;
import view.components.LoadProjectDialog;
import view.components.SaveImageDialog;
import view.components.SaveProjectDialog;

public class BiomorphController {

	private AbstractBiomorph model;
	private BiomorphInterface view; 
	
	public BiomorphController(BiomorphInterface view, AbstractBiomorph biomorph) {
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
		view.addLoadProjectListener(new LoadProjectListener());
		view.addHallOfFameViewListener(new HallOfFameListener());
		view.addHallOfFameAddListener(new AddToHoFListener());
		view.addGenomeChangeListener(new UpdateGenomeColourListener());
		view.addSaveImageListener(new SaveImageListener());
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
	
	class UpdateGenomeColourListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			GenomeViewUpdateModel genomeUpdate = view.getGenomeUpdate();
			
			for(Genome genome : model.getGenome()) {
				Color color;
				
				if(genomeUpdate.isRandomColours()) {
					color = getRandomColor();
				} else {
					color = new Color(genomeUpdate.getRed(), genomeUpdate.getGreen(), genomeUpdate.getBlue());
				}
				
				genome.setColour(color);
			}
			
			view.updateMutations(getMutatedBiomorphs());
		}
		
		private Color getRandomColor() {
			Random rand = new Random();
			
			return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
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
			new SaveProjectDialog<Genome>(view.getFrame(), model.getGenome()).saveFile();
		}
		
	}
	
	/**
	 * Action to save when a biomorph when save button is pressed.
	 */
	class SaveImageListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			new SaveImageDialog<Genome>(view.getFrame(), model.getGenome(), view.getBiomorphPanel()).saveFile();
		}
		
	}
	
	/**
	 * Action to load a biomorph from file.
	 */
	class LoadProjectListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			Genome genome = new LoadProjectDialog<Genome>(view.getFrame()).loadFile();
			
			model.setGenome(genome);
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	class HallOfFameListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			new HallOfFameGUI().displayGui();
		}
		
	}
	
	class AddToHoFListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			String currentDirectory = System.getProperty("user.dir");
			
			File file = new File(currentDirectory + HallOfFameGUI.HALL_OF_FAME_SUBDIRECTORY);
			
			int savedBiomorphCount = countSavedBiomorphs(file);
			
			if(savedBiomorphCount < 9) { // can only save 9 biomorphs
				try {
					new FileSerializer<Genome>().serialiseFile(model.getGenome(), file.getAbsolutePath() + "/biomorph" + (savedBiomorphCount+1));
				} catch (IOException e) {
					System.out.println("Error saving to hall of fame");
				}
			} else {
				String message = "No more biomorphs can be added to the hall of fame. Please delete some existing biomorphs.";
				
				JOptionPane.showMessageDialog(view.getFrame(), message, "Error adding to Hall of Fame", JOptionPane.WARNING_MESSAGE);
			}
		}
	
		private int countSavedBiomorphs(File current) {
			int savedBiomorphCount = 0;
			
			for(@SuppressWarnings("unused") File currentFile : current.listFiles()) {
				savedBiomorphCount++;
			};
		
			return savedBiomorphCount;
		}
		
	}
	
	/**
	 * Creates an instance of the controller, with a view and model.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		BiomorphInterface view = new AdvancedGUI(model);
		
		new BiomorphController(view, model);
	}
	
}