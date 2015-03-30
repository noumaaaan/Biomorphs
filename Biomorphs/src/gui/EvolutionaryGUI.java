package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import main.BiomorphPanel;
import main.Genome;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */

@SuppressWarnings("serial") 
public class EvolutionaryGUI extends AbstractGUI {
	private JFrame windowFrame;

	private final int gridRows = 3;
	private final int gridCols = 3;

	public EvolutionaryGUI() {
		super();
		
		//Create the window for the application
		windowFrame = new JFrame("Evolutionary Art: Final (STAGE 2)");  
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setPreferredSize(new Dimension(800,600));
		windowFrame.setResizable(true);

		BiomorphPanel activeBiomorphPanel = new BiomorphPanel();
		
		windowFrame.add(createMenuBar(activeBiomorphPanel), BorderLayout.NORTH);

		final JPanel biomorphs = new JPanel(new GridLayout(1, 2));
		biomorphs.add(createBiomorphGrid());
		biomorphs.add(activeBiomorphPanel);

		windowFrame.add(biomorphs);
		windowFrame.setVisible(true);

		windowFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event){
				exitApplication(windowFrame);
			}
		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	private JMenuBar createMenuBar(final BiomorphPanel panel) {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu biomorphMenu = new JMenu("Biomorph");
		
		JMenuItem newBiomorph = new JMenuItem("New biomorph");
		newBiomorph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getBiomorph().evolve(new Genome()); // placeholder behaviour for now until evolving implemented
				panel.refresh();
			}
		});
		biomorphMenu.add(newBiomorph);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(biomorphMenu);

		return menuBar;
	}

	private JPanel createBiomorphGrid() {
		//Defining the Draw Canvas
		JPanel biomorphGrid = new JPanel(new GridLayout(gridRows, gridCols));

		for(int i = 0; i < (gridRows * gridCols); i++) {
			BiomorphPanel panel = new BiomorphPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

			biomorphGrid.add(panel);	
		}
		
		return biomorphGrid;
	}

}