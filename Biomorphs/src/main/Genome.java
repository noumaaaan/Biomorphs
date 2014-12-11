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

	private Genome parent; // represents the parent biomorph's specific genome

	public Genome() {
		angle = GENOME_DEFAULT_ANGLE;
		length = GENOME_DEFAULT_LENGTH;
		colour = GENOME_DEFAULT_COLOUR;
		parent = null;
	}
	
	public Genome(Genome genome) {
		this.angle = genome.angle;
		this.length = genome.length;
		this.colour = genome.colour;
		
		if(genome.parent != null)
			this.parent = genome.parent.clone();
		else
			this.parent = null;
				
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

	public void setParent(Genome parent) {
		this.parent = parent;
	}

	public Genome getParent() {
		return parent;
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
	
	
	/**
	 * An iterator for iterating through a genome tree.
	 * 
	 * @author Alex Luckett <lucketta@aston.ac.uk>
	 *
	 */
	private class GenomeIterator implements Iterator<Genome> {
		private Genome genome;

		public GenomeIterator(Genome genome) {
			this.genome = genome;
		}

		@Override
		public boolean hasNext() {
			return (genome.getParent() != null);
		}

		@Override
		public Genome next() {
			Genome current = genome.clone();

			genome = genome.getParent();

			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove genome");
		}

	}
}
