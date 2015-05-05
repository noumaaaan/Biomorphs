package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.AbstractBiomorph;
import model.EvolutionaryBiomorph;
import model.FileSerializer;
import model.Genome;

public class HallOfFameGUI extends AbstractGUI {

	public HallOfFameGUI() {
		super("Biomorph Hall of Fame", 1225, 640);
		
		JPanel biomorphContainer = new JPanel();
		
		String currentPath = System.getProperty("user.dir"); // get current directory
		
		loadBiomorphs(biomorphContainer, currentPath);
		
		windowFrame.add(biomorphContainer);
		
		windowFrame.setVisible(true);		
		windowFrame.pack();
	}
	
	private void loadBiomorphs(JPanel container, String currentPath) {
		try {
			for(File file : new File(currentPath + "/hallOfFame").listFiles()) {
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
			
			BiomorphPanel biomorphPanel = new BiomorphPanel();
			biomorphPanel.setBiomorph(biomorph);
			
			container.add(biomorphPanel);
			
			biomorphPanel.refresh();
		} catch (Exception e) {
			String message = "Unable to load file: " + filePath;
			
			JOptionPane.showMessageDialog(windowFrame, message, "Unable to load biomorph", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}

	@Override
	public void addGenerateListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMutateListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUpdateBiomorphListener(MouseListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addExitListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addHelpListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLoadProjectListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSaveProjectListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSaveImageListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractBiomorph getMutatedBiomorph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addHallOfFameListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

}
