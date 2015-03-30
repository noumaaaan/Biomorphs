package main;

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
	
	public void evolve(Genome genome) {
		this.genome.append(genome);
	}

}
