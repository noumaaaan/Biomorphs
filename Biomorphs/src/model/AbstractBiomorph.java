package model;
import static model.Constants.DEFAULT_GENOME_SIZE;
import static model.Constants.GENOME_MINIMUM_EVOLUTIONS;

import java.util.Random;


/**
 * Represents a biomorph
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractBiomorph {
	protected Genome genome;
	
	public AbstractBiomorph( Genome genome) {
		this.genome = genome.clone();
	}
	
	/**
	 * Creates a biomorph at the default position (0, 0)
	 */
	public AbstractBiomorph() {
		genome = new Genome(true);
	}
	
	public void setGenome(Genome genome) {
		this.genome = genome;
	}
	
	public Genome getGenome() {
		return genome;
	}
	
	/**
	 * Generates a random amount of children to the biomorph's genome
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
	 */
	protected abstract void evolve(Genome genome);
	
	/**
	 * Mutates the biomorph using a random genome
	 */
	public void mutate() {
		evolve(new Genome(true));
	}
	
}
