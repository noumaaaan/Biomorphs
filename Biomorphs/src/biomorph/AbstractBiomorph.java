package biomorph;
import static biomorph.Constants.*;

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
		genome = new Genome(true);
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
	
	/**
	 * Generates a random amount of children to the biomorph's genome.
	 * 
	 * @return
	 */
	public void generateChildren() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations
		
		Genome current = genome;			
		for(int i = 0; i < evolutions; i++) {
			Genome newChild = new Genome(true);
			current.setChild(newChild); // randomly generated child
			
			current = newChild;
		}
	}
	
	/**
	 * Controls how a biomorph evolves, given a genome.
	 * 
	 * @param genome
	 * @throws InvalidGenomePositionException 
	 */
	protected abstract void evolve(Genome genome);
	
	/**
	 * Mutates the biomorph using a random genome.
	 * 
	 * @throws InvalidGenomePositionException 
	 */
	public void mutate() {
		evolve(new Genome(true));
	}
}
