package gui;

import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import biomorph.AbstractBiomorph;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */

public class EvolutionaryGUI extends AbstractGUI {
	private final BiomorphPanel activeBiomorphPanel;

	private final int gridRows = 3;
	private final int gridCols = 3;

	public EvolutionaryGUI() {
		super("Biomorph Mutation: Advanced User", 800, 600);

		activeBiomorphPanel = new BiomorphPanel();
		//activeBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//windowFrame.add(createMenuBar(activeBiomorphPanel), BorderLayout.NORTH); // TODO re-enable once actually needed

		final JPanel biomorphs = new JPanel(new GridLayout(1, 2));
		biomorphs.add(createBiomorphGrid());
		biomorphs.add(activeBiomorphPanel);

		windowFrame.add(biomorphs);
		windowFrame.setVisible(true);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	private JMenuBar createMenuBar(final BiomorphPanel panel) { // don't delete this - modify and uncomment method in constructor when needed
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		return menuBar;
	}

	private JPanel createBiomorphGrid() {
		//Defining the Draw Canvas
		AbstractBiomorph biomorph = activeBiomorphPanel.getBiomorph();
		
		BiomorphGrid biomorphGrid = new BiomorphGrid(
				gridRows, gridCols, biomorph, activeBiomorphPanel);
		
		biomorphGrid.setupGrid(gridRows * gridCols);
		
		return biomorphGrid;
	}

}