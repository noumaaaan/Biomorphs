package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import model.AbstractBiomorph;
import model.EvolutionaryBiomorph;
import model.RandomBiomorph;
import view.AdvancedGUI;
import view.Viewable;

public class BiomorphController {

	private AbstractBiomorph model;
	private Viewable view; 
	
	public BiomorphController(Viewable view, AbstractBiomorph biomorph) {
		this.view = view; 
		this.model = biomorph;
		
		view.updateMutations(getMutatedBiomorphs());
		
		view.addMutateListener(new MutateListener());
		view.addExitListener(new ExitListener());
		view.addUpdateBiomorphListener(new UpdateBiomorphListener());
	}
	
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		Viewable view = new AdvancedGUI(model);
		
		new BiomorphController(view, model);
	}
	
	private List<AbstractBiomorph> getMutatedBiomorphs() {
		ArrayList<AbstractBiomorph> biomorphs = new ArrayList<AbstractBiomorph>();
		
		for(int i = 0; i < 9; i++) {
			AbstractBiomorph biomorph = new EvolutionaryBiomorph(model.getGenome());
			biomorph.mutate();
			
			biomorphs.add(biomorph);
		}
		
		return biomorphs;
	}
	
	class MutateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.mutate();
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.exitApplication();
		}
		
	}
	
	class UpdateBiomorphListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			model.setGenome(view.getMutatedBiomorph().getGenome());
			
			view.updateMutations(getMutatedBiomorphs());
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
}