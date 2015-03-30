/**
 * 
 */
package main;

/**
 * Represents the default biomorph type.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class RandomBiomorph extends AbstractBiomorph {

	public RandomBiomorph(double d, double e) {
		super(d, e);
	}

	public RandomBiomorph() {
		super();
	}

	@Override
	public void evolve(Genome genome) {	} // this biomorph type won't evolve!
}
