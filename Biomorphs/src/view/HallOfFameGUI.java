package view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.EvolutionaryBiomorph;
import model.FileSerializer;
import model.Genome;

public class HallOfFameGUI extends AbstractGUI {
	
	public static final String HALL_OF_FAME_SUBDIRECTORY = "/hallOfFame";

	public HallOfFameGUI() {
		super("Biomorph Hall of Fame", 600, 400);
		windowFrame.setResizable(true);
		
		JPanel biomorphContainer = new JPanel();
		
		String currentPath = System.getProperty("user.dir"); // get current directory
		
		loadBiomorphs(biomorphContainer, currentPath);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("TEST"));
		
		windowFrame.add(biomorphContainer, BorderLayout.CENTER);
		windowFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}
	
	private void loadBiomorphs(JPanel container, String currentPath) {
		try {
			for(File file : new File(currentPath + HALL_OF_FAME_SUBDIRECTORY).listFiles()) {
				processFile(file.getAbsolutePath(), container);
			}
		} catch (Exception e) {
			String message = "No biomorphs exist within the hall of fame";
			
			JOptionPane.showMessageDialog(windowFrame, message, "Unable to load biomorph", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void processFile(String filePath, JPanel container) {
		try {
			Genome genome = new FileSerializer<Genome>().deserialiseFile(filePath);
			
			EvolutionaryBiomorph biomorph = new EvolutionaryBiomorph(genome);
			
			BiomorphPanel biomorphPanel = new BiomorphPanel(biomorph, false);
			biomorphPanel.setBiomorph(biomorph);
			
			container.add(biomorphPanel);
		} catch (Exception e) {
			String message = "Unable to load file: " + filePath;
			
			JOptionPane.showMessageDialog(windowFrame, message, "Unable to load biomorph", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
