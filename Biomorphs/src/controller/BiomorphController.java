package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AbstractBiomorph;
import model.RandomBiomorph;
import view.AdvancedGUI;
import view.Viewable;

public class BiomorphController {

	private AbstractBiomorph model;
	private Viewable view; 
	
	public BiomorphController(Viewable view, AbstractBiomorph biomorph){
		this.view = view; 
		this.model = biomorph;
		
		view.addMutateListener(new MutateListener());
		view.addExitListener(new ExitListener());
	}
	
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		Viewable view = new AdvancedGUI(model);
		
		new BiomorphController(view, model);
	}
	
	class MutateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.mutate();
		}
		
	}
	
	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.exitApplication();
		}
		
	}
	
	
	
}