package controller;

import model.AbstractBiomorph;
import model.RandomBiomorph;
import view.BasicGUI;
import view.Viewable;

import java.awt.event.*;

public class BiomorphController {

	private AbstractBiomorph model;
	private Viewable view; 
	
	public BiomorphController(Viewable view, AbstractBiomorph biomorph){
		this.view = view; 
		this.model = biomorph;
		
		this.view.addMutateListener(new MutateListener());
	}
	
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		Viewable view = new BasicGUI(model);
		
		new BiomorphController(view, model);
	}
	
	class MutateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("mutated");
			model.mutate();
		}
		
	}
	
}