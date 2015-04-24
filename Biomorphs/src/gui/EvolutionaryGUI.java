package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import biomorph.AbstractBiomorph;
import biomorph.EvolutionaryBiomorph;
import biomorph.Genome;
import biomorph.GenomeSerializer;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 * 
 */

public class EvolutionaryGUI extends AbstractGUI {
	private final BiomorphPanel activeBiomorphPanel;
	private final BiomorphGrid biomorphGrid;

	private final int gridRows = 3;
	private final int gridCols = 3;

	public EvolutionaryGUI() {
		this(null);
	}
	
	public EvolutionaryGUI(Genome activeGenome) {
		super("Evolutionary Art: Final (STAGE 2)", 800, 600);

		if(activeGenome != null) {
			AbstractBiomorph bio = new EvolutionaryBiomorph();
			bio.setGenome(activeGenome);
			
			activeBiomorphPanel = new BiomorphPanel(bio, false);
		} else {
			activeBiomorphPanel = new BiomorphPanel();
		}
		
		activeBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		biomorphGrid = createBiomorphGrid(activeBiomorphPanel);

		windowFrame.add(createMenuBar(activeBiomorphPanel), BorderLayout.NORTH); // TODO re-enable once actually needed

		final JPanel innerPanel = new JPanel(new GridLayout(1, 2));
		innerPanel.add(biomorphGrid);
		innerPanel.add(activeBiomorphPanel);

		windowFrame.add(innerPanel);
		windowFrame.setVisible(true);

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	private JMenuBar createMenuBar(final BiomorphPanel panel) { // don't delete this - modify and uncomment method in constructor when needed
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem openBiomorph = new JMenuItem("Open project");
		openBiomorph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Genome genome = GenomeSerializer.deserialiseGenome("PATH HERE"); // TODO nouman: make a window the user can select a file from, then use the path from that
					
					new EvolutionaryGUI(genome).displayGui();
					EvolutionaryGUI.super.destroyGui();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		fileMenu.add(openBiomorph);
		
		JMenuItem saveBiomorph = new JMenuItem("Save project");
		saveBiomorph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {								
				try {
					GenomeSerializer.serialiseGenome(activeBiomorphPanel.getBiomorph().getGenome(), "PATH HERE"); // TODO nouman: make a window the user can select a file from, then use the path from that
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		fileMenu.add(saveBiomorph);
		
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		return menuBar;
	}

	private BiomorphGrid createBiomorphGrid(BiomorphPanel activeBiomorphPanel) {
		//Defining the Draw Canvas
		
		BiomorphGrid biomorphGrid = new BiomorphGrid(gridRows, gridCols, activeBiomorphPanel);
		
		biomorphGrid.setupGrid(gridRows * gridCols);
		
		return biomorphGrid;
	}

}