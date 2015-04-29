/**
 * 
 */
package model;

/**
 * Represents the default biomorph type.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class RandomBiomorph extends AbstractBiomorph {

	public RandomBiomorph() {
		super();
	}

	@Override
	protected void evolve(Genome genome) {
		super.generateChildren();
	}
}
