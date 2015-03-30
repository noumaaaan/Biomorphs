package main;
import java.awt.Color;
import java.util.Iterator;

import static main.Constants.*;

/**
 * Represents a genome structure, to be used within a biomorph.
 * 
 * Contains a history of each parent biomorph's genome.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 *
 */
public class Genome implements Iterable<Genome>, Cloneable {
	private double angle;
	private int length;
	private Color colour;

	private Genome child; // represents the parent biomorph's specific genome

	public Genome() {
		angle = GENOME_DEFAULT_ANGLE;
		length = GENOME_DEFAULT_LENGTH;
		colour = GENOME_DEFAULT_COLOUR;
		child = null;
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
		Genome finalGenome = getLastGenome(this);		
		Genome finalMerging = getLastGenome(genome).clone();
		
		printGenomeHistory("BEFORE");
		
		finalGenome.setChild(finalMerging);
		
		printGenomeHistory("AFTER");
	}
	
	private Genome getLastGenome(Genome genome) {
		Genome lastGenome = null;
		
		for(Genome currentGenome : genome) {
			lastGenome = currentGenome;
		}
		
		return lastGenome;
	}
	
	private void printGenomeHistory(String prefix) {
		for(Genome genome : this) {
			System.out.println(prefix + " " + genome);
		}
	}

	public void setAngle(double angle) {		
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	public void setLength(int length) {
		//if(length > GENOME_MAX_LENGTH)
			//throw new IllegalArgumentException("Genome length cannot be greater than 10");
			
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public Color getColour() {
		return colour;
	}

	public void setChild(Genome parent) {
		this.child = parent;
	}

	public Genome getChild() {
		return child;
	}
	
	public int size() {
		int size = 0;
		
		for(Genome genome : this) {
			size++;
		}
		
		return size;
	}
	
	@Override
	public String toString() {
		return "Angle " + angle + ", length " + length + ", colour " + colour.toString();
	}

	/**
	 * Returns a deep clone of the current genome, including parents by definition.
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
