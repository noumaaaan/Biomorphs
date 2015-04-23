package biomorph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GenomeSerializer {

	private GenomeSerializer() { } // doesn't need instantiating
	
	public static void serialiseGenome(Genome genome, String path) throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(path);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		
		objectOutput.writeObject(genome);
		
		objectOutput.close();
		fileOutput.close();
	}
	
	public static Genome deserialiseGenome(String path) throws IOException, ClassNotFoundException {
		FileInputStream fileInput = new FileInputStream(path);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        
        Genome genome = (Genome) objectInput.readObject();
        
        objectInput.close();
        fileInput.close();
        
        return genome;
	}
	
}
