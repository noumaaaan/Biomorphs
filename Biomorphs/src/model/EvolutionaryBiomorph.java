package model;

import java.util.Random;

public class EvolutionaryBiomorph extends AbstractBiomorph {
	
	public EvolutionaryBiomorph() {
		super();
	}

	public EvolutionaryBiomorph(Genome genome) {
		super(genome);
	}
	
	/**
	 * Inserts a new genome randomly within the biomorph
	 */
	protected void evolve(Genome genome) {
		Random rand = new Random();
		int position = rand.nextInt(getGenome().size());
		
		getGenome().get(position).insert(genome);
	}

}
