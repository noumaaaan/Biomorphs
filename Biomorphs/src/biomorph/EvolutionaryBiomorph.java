package biomorph;

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
	 * Appends the new genome to the end of the current.
	 */
	protected void evolve(Genome genome) {
		getGenome().append(genome);
	}

}
