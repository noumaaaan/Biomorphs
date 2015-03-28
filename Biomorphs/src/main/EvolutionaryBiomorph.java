package main;

import static main.Constants.DEFAULT_GENOME_SIZE;
import static main.Constants.GENOME_MAX_LENGTH;
import static main.Constants.GENOME_MINIMUM_EVOLUTIONS;

import java.awt.Color;
import java.util.Random;

public class EvolutionaryBiomorph extends AbstractBiomorph {
	
	public void evolve(Genome geneome) {
		this.genome.append(genome);
		//this.genome.setChild(genome);
	}
	
	/**
	 * Generates a genome tree for the biomorph
	 * 
	 * @return iterationCount
	 */
	public int generateChildren() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations. capped at 10 for demo purposes, and never 0 (so +1).
		
		Genome current = new Genome();
		for(int i = 0; i <= evolutions; i++) {
			Genome newParent = new Genome();
			
			newParent.setAngle(rand.nextInt() * rand.nextDouble());
			newParent.setLength(rand.nextInt(GENOME_MAX_LENGTH));
			newParent.setColour(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // random values for RGB
			
			current.setChild(newParent);
			
			if(i == 0)
				genome = current; // need to save our first generation!
			else
				current = current.getChild();
		}
		
		return evolutions;
	}

}
