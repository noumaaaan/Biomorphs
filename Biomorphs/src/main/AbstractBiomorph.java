package main;
import static main.Constants.DEFAULT_GENOME_SIZE;
import static main.Constants.GENOME_MINIMUM_EVOLUTIONS;

import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Represents a biomorph
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractBiomorph {
	protected Genome genome;
	private Point2D origin;

	/**
	 * Constructs a biomorph
	 * 
	 * @param x coordinate on canvas
	 * @param y coordinate on canvas
	 */
	public AbstractBiomorph(double x, double y) {
		genome = new Genome();
		origin = new Point2D.Double(x, y);
	}
	
	public AbstractBiomorph(double x, double y, Genome genome) {
		this.genome = genome.clone();
		origin = new Point2D.Double(x, y);
	}
	
	/**
	 * Creates a biomorph at the default position (0, 0)
	 */
	public AbstractBiomorph() {
		this(0, 0);
	}
	
	public AbstractBiomorph(AbstractBiomorph bio) {
		this(bio.getPosition().getX(), bio.getPosition().getY(), bio.getGenome());
	}
	
	public void setPosition(double x, double y) {
		origin.setLocation(x, y);
	}
	
	public Point2D getPosition() {
		return origin;
	}
	
	public void setGenome(Genome genome) {
		this.genome = genome;
	}
	
	public Genome getGenome() {
		return genome;
	}
	
	public abstract void evolve(Genome genome);
	
	public int generateChildren() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations. capped at 10 for demo purposes, and never 0 (so +1).
		
		Genome current = new Genome();
		this.genome = current;
				
		for(int i = 0; i <= evolutions; i++) {
			Genome newParent = new Genome(true);			
			current.setChild(newParent);
			
			current = current.getChild();
		}
		
		return evolutions;
	}
	
	public void mutate() {		
		for(int i = 0; i < 50; i++) {
			Genome mutation = new Genome(true); // FIXME - random mutation not mutating!
			
			evolve(mutation);
		}
	}
}
