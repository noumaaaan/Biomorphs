package main;
import java.awt.geom.Point2D;


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
	 * @param d coordinate on canvas
	 * @param e coordinate on canvas
	 */
	public AbstractBiomorph(double d, double e) {
		genome = new Genome();
		origin = new Point2D.Double(d, e);
	}
	
	/**
	 * Creates a biomorph at the default position (0, 0)
	 */
	public AbstractBiomorph() {
		this(0, 0);
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
	 * Generates a genome tree for the biomorph
	 * 
	 * @return iterationCount
	 */
	public abstract int generateParents();
}
