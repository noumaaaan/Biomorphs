/**
 * 
 */
package main;

import static main.Constants.*;

import java.awt.Color;
import java.util.Random;

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
	public int generateParents() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations. capped at 10 for demo purposes, and never 0 (so +1).
		
		Genome current = new Genome();
		for(int i = 0; i <= evolutions; i++) {
			Genome newParent = new Genome();
			
			newParent.setAngle(rand.nextInt() * rand.nextDouble());
			newParent.setLength(rand.nextInt(GENOME_MAX_LENGTH));
			newParent.setColour(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // random values for RGB
			
			current.setParent(newParent);
			
			if(i == 0)
				genome = current; // need to save our first generation!
			else
				current = current.getParent();
		}
		
		return evolutions;
	}

}
