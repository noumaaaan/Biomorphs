package main;
import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

import static main.Constants.*;

/**
 * Represents a genome structure, to be used within a biomorph.
 * 
 * Contains a history of each child biomorph's genome.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class Genome implements Iterable<Genome>, Cloneable {
	private double angle;
	private int length;
	private Color colour;

	private Genome child; // chain from parent to final child

	public Genome() {
		this(false);
	}

	public Genome(boolean random) {
		if(random) {
			Random rand = new Random();

			angle = rand.nextInt() * rand.nextDouble();
			length = rand.nextInt(GENOME_MAX_LENGTH);
			colour = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			child = null;
		} else {
			angle = GENOME_DEFAULT_ANGLE;
			length = GENOME_DEFAULT_LENGTH;
			colour = GENOME_DEFAULT_COLOUR;
			child = null;
		}
	}

	public Genome(Genome genome) {
		this.angle = genome.angle;
		this.length = genome.length;
		this.colour = genome.colour;

		if(genome.child != null) {
			this.child = genome.child.clone();
		} else {
			this.child = null;
		}	
	}

	public void append(Genome genome) {
		Genome finalGenome = getLastGenome();		
		Genome finalMerging = genome.getLastGenome().clone();
		
		System.out.println("CURRENT LAST: " + finalGenome);
		System.out.println("NEW LAST    : " + finalMerging);

		finalGenome.setChild(finalMerging);
	}

	public Genome getLastGenome() {
		Genome lastGenome = this;
		
		while(lastGenome.getChild() != null) {
			lastGenome = lastGenome.getChild();
		}

		return lastGenome;
	}

	public void setAngle(double angle) { this.angle = angle; }
	public double getAngle() {	return angle; }
	public void setLength(int length) { this.length = length; }
	public int getLength() { return length; }
	public void setColour(Color colour) { this.colour = colour; }
	public Color getColour() { return colour; }
	public void setChild(Genome child) { this.child = child; }
	public Genome getChild() { return child; }

	public int size() {
		int size = 0;

		for(@SuppressWarnings("unused") Genome genome : this) { 
			size++;
		}

		return size;
	}

	@Override
	public String toString() {
		return "Angle " + angle + ", "
			+ "length " + length + ", "
			+ "colour " + colour.toString();
	}

	/**
	 * Returns a deep clone of the current genome, including children.
	 */
	@Override
	public Genome clone() {		
		return new Genome(this);
	}

	@Override
	public Iterator<Genome> iterator() {
		return new GenomeIterator(this);
	}
}
