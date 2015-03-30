package main;

public class EvolutionaryBiomorph extends AbstractBiomorph {
	
	public EvolutionaryBiomorph(double d, double e) {
		super(d, e);
	}
	
	public EvolutionaryBiomorph() {
		super();
	}
	
	public void evolve(Genome genome) {
		this.genome.append(genome);
	}

}
