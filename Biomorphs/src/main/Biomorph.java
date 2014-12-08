package main;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import static main.Constants.*;


/**
 * Represents a biomorph
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class Biomorph {
	public static final int DEFAULT_GENOME_SIZE = 50;
	
	private Genome genome;
	private Point origin;

	/**
	 * Constructs a biomorph
	 * 
	 * @param x coordinate on canvas
	 * @param y coordinate on canvas
	 */
	public Biomorph(int x, int y) {
		genome = new Genome();
		origin = new Point(x, y);
	}
	
	/**
	 * Creates a biomorph at the default position (0, 0)
	 */
	public Biomorph() {
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
	
	public Point getPosition() {
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
	public int generateRandomParents() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations. capped at 10 for demo purposes, and never 0 (so +1).
		
		Genome current = new Genome();
		for(int i = 0; i < evolutions; i++) {
			Genome newParent = new Genome();
			
			newParent.setAngle(rand.nextInt() * (rand.nextDouble()*10)); // ensure angle is not negative
			newParent.setLength(rand.nextInt(GENOME_MAX_LENGTH));
			newParent.setColour(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // random values for RGB
			
			current.setParent(newParent);
			
			if(i == 0)
				genome = current;
			else
				current = current.getParent();
		}
		
		return evolutions;
	}
}
