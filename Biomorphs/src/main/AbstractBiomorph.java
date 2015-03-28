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
	 * @param x coordinate on canvas
	 * @param y coordinate on canvas
	 */
	public AbstractBiomorph(double x, double y) {
		genome = new Genome();
		origin = new Point2D.Double(x, y);
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
	
	public abstract void evolve(Genome genome);
}
