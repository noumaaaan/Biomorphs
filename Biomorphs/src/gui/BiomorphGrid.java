package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.InvalidParameterException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import biomorph.AbstractBiomorph;
import biomorph.EvolutionaryBiomorph;

/**
 * A panel to draw a biomorph. 
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
@SuppressWarnings("serial")
public class BiomorphGrid extends JPanel {
	private int rows = 0;
	private int cols = 0;

	private AbstractBiomorph baseBiomorph;
	private final BiomorphPanel activeBiomorphPanel;

	public BiomorphGrid(int rows, int cols, AbstractBiomorph baseBiomorph, BiomorphPanel activeBiomorphPanel) {
		super(new GridLayout(rows, cols));
		
		this.rows = rows;
		this.cols = cols;
		this.baseBiomorph = baseBiomorph;
		this.activeBiomorphPanel = activeBiomorphPanel;
	}

	/**
	 * Populates the grid with a number of cells. Constructs BiomorphPanels within the cells
	 * with evolutionary biomorphs.
	 * 
	 * @param cellCount
	 */
	public void setupGrid(int cellCount) {
		int maxCells = rows * cols;

		if(cellCount > maxCells) {
			throw new InvalidParameterException("Cell count cannot be greater than the available amount (" + maxCells + ")");
		}

		for(int i = 0; i < cellCount; i++) {
			AbstractBiomorph bio = new EvolutionaryBiomorph(baseBiomorph);
			final BiomorphPanel panel = new BiomorphPanel(bio, false);
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

			panel.addMouseListener(new MouseListener() {

				@Override
				public void mousePressed(MouseEvent e) { }

				@Override
				public void mouseExited(MouseEvent e) { }

				@Override
				public void mouseEntered(MouseEvent e) { }

				@Override
				public void mouseClicked(MouseEvent e) { }

				@Override
				public void mouseReleased(MouseEvent e) {
					activeBiomorphPanel.setBiomorph(panel.getBiomorph()); // user has selected new biomorph to become the active
					activeBiomorphPanel.refresh();
					
					BiomorphGrid.this.newBiomorphs();
					BiomorphGrid.this.mutateBiomorphs(); // mutate all panels
				}
			});

			BiomorphGrid.this.add(panel);
		}
		
		this.mutateBiomorphs();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	/**
	 * Mutates each biomorph within the grid.
	 * 
	 * TODO update all panels with new biomorph based on active, once
	 * mutation has occurred.
	 */
	private void mutateBiomorphs() {
		for(Component component : this.getComponents()) {
			BiomorphPanel panel = (BiomorphPanel) component;
			
			panel.getBiomorph().mutate(); 
			panel.refresh();
		}
	}
	
	private void newBiomorphs() {
		removeAll();
		repaint();
				
		baseBiomorph = activeBiomorphPanel.getBiomorph();
		
		setupGrid(rows * cols);
	}
	
	
}
