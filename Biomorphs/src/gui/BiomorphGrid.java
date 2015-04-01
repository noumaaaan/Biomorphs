package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import main.AbstractBiomorph;
import main.EvolutionaryBiomorph;
import main.Genome;

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
					AbstractBiomorph activeBiomorph = activeBiomorphPanel.getBiomorph();
					Genome mergingGenome = panel.getBiomorph().getGenome();

					activeBiomorph.evolve(mergingGenome); // merge genome into our active biomorph

					activeBiomorphPanel.refresh();
					
					BiomorphGrid.this.mutate(); // mutate all panels
				}
			});

			BiomorphGrid.this.add(panel);
		}
		
		this.mutate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * TODO update all panels with new biomorph based on active, once
	 * mutation has occurred.
	 */
	public void mutate() {
		for(Component component : this.getComponents()) {
			BiomorphPanel panel = (BiomorphPanel) component;
			
			panel.getBiomorph().mutate(); 
			panel.refresh();
		}
	}
}
