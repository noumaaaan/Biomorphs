package biomorph;

import java.util.Random;

public class EvolutionaryBiomorph extends AbstractBiomorph {
	
	public EvolutionaryBiomorph(double x, double y) {
		super(x, y);
	}

	public EvolutionaryBiomorph(AbstractBiomorph bio) {
		super(bio.getPosition().getX(), bio.getPosition().getY(), bio.getGenome().clone());
	}
	
	public EvolutionaryBiomorph() {
		super();
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
