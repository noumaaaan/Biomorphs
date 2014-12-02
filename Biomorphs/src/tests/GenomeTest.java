package tests;
import static org.junit.Assert.*;
import main.Biomorph;
import main.Genome;
import org.junit.Test;

public class GenomeTest {
	
	/**
	 * Tests to ensure randomly generated biomorphs have a correct gene tree.
	 */
	@Test
	public void testIterator() {
		Biomorph biomorph = new Biomorph();
		int totalParents = biomorph.generateRandomParents();
		int count = 0;
		
		for(@SuppressWarnings("unused") Genome genome : biomorph.getGenomes()) {
			System.out.println(genome);
			count++;
		}
		
		assertTrue(totalParents == count);
	}
	
	/**
	 * Tests to ensure that a genome clone returns a brand new object, rather than referencing the old.
	 */
	@Test
	public void testClone() {
		Genome gene1 = new Genome();
		Genome gene2 = gene1.clone();
		Genome gene3 = new Genome(gene1);
		Genome gene4 = gene3.clone();
		
		assertTrue(gene1 != gene2 && gene1 != gene3 && gene3 != gene4);
	}
}
