package main;
import java.awt.geom.Point2D;


/**
 * Represents a biomorph
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractBiomorph {
	public static final int DEFAULT_GENOME_SIZE = 100;
	
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
	
	public void setX(int x) {
		origin.setLocation(x, origin.getY());
	}
	
	public void setY(int y) {
		origin.setLocation(origin.getX(), y);
	}
	
	public void setPosition(int x, int y) {
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
	 * Generates a random genome tree for the biomorph
	 * 
	 * @return iterationCount
	 */
	public abstract int generateRandomParents();
}
