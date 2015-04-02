/**
 * 
 */
package biomorph;

/**
 * Represents the default biomorph type.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class RandomBiomorph extends AbstractBiomorph {

	public RandomBiomorph(double x, double y) {
		super(x, y);
	}

	public RandomBiomorph() {
		super();
	}

	@Override
	protected void evolve(Genome genome) {
		super.generateChildren();
	}
}
