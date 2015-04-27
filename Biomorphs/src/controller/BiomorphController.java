package controller;

import model.AbstractBiomorph;
import model.RandomBiomorph;
import view.BasicGUI;
import view.Viewable;

public class BiomorphController {

	private AbstractBiomorph biomorph;
	private Viewable view; 
	
	public BiomorphController(Viewable view, AbstractBiomorph biomorph){
		this.view = view; 
		this.biomorph = biomorph;
	}
	
	public void setMainBiomorph(AbstractBiomorph bio){
		biomorph = bio;
	}
	
	public AbstractBiomorph getBiomorph(){
		return biomorph;
	}
	
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		Viewable view = new BasicGUI(model);
		
		BiomorphController controller = new BiomorphController(view, model);
	}
	//public void selectBiomorph(){
	//}
	
}