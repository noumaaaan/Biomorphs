package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

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
public class BiomorphPanel extends JPanel {
	AbstractBiomorph biomorph;
	
	public BiomorphPanel() {
		this(new EvolutionaryBiomorph(), true); // default use evolutionary
	}
	
	public BiomorphPanel(AbstractBiomorph biomorph, boolean generateChildren) {
		super();
		this.setBackground(Color.WHITE);
		
		this.biomorph = (EvolutionaryBiomorph) biomorph;
		
		if(generateChildren)
			this.biomorph.generateChildren();
	}
	
	private void centreBiomorph() {
		double canvasWidth = super.getSize().getWidth();
		double canvasHeight = super.getSize().getHeight();
		
		biomorph.setPosition(canvasWidth/2, canvasHeight/2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // do not remove this - clears the previous canvas
		
		centreBiomorph();
				
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2f));
				
		double startX = biomorph.getPosition().getX();
		double startY = biomorph.getPosition().getY();
		
		drawSection(startX, startY, biomorph, DrawSection.LEFT, g2d);		
		drawSection(startX, startY, biomorph, DrawSection.RIGHT, g2d);
	}
	
	/**
	 * Draws a biomorph on one side of the panel. 
	 * 
	 * @param startX start position on x axis
	 * @param startY start position on y axis
	 * @param biomorph biomorph to draw
	 * @param isRightSide indicates true for right side of page, false for left
	 * @param g2d graphics to draw with
	 */
	private void drawSection(double startX, double startY, 
			AbstractBiomorph biomorph, DrawSection section, Graphics2D g2d) {		
		double lastX = startX;
		double lastY = startY;
		
		for(Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());
		
			double sinAngle = Math.sin(genome.getAngle()); // used to calculate end point on X axis
			double cosAngle = Math.cos(genome.getAngle()); // used to calculate end point on Y axis
		
			double endX = genome.getLength() * sinAngle;
			double endY = lastY + (genome.getLength() * cosAngle); // always the same -> want it symmetrical along y axis
			
			if(section == DrawSection.RIGHT) { // need to invert if left side (+ and -)
				endX = lastX + endX;
			} else {
				endX = lastX - endX;
			}
			
			g2d.draw(new Line2D.Double(lastX, lastY, endX, endY)); // draw the line
			
			lastX = endX; lastY = endY; // update start position for next line to use
		}
	}
	
	public void newBiomorph() {
		newBiomorph(new EvolutionaryBiomorph());
	}
	
	public void newBiomorph(AbstractBiomorph biomorph) {
		biomorph.generateChildren();
		
		this.biomorph = biomorph;
	}
	
	public AbstractBiomorph getBiomorph() {
		return biomorph;
	}
	
	public void refresh() {
		//this.revalidate();
		this.repaint();
	}
	
	/**
	 * Represents the section of a panel to draw on.
	 * 
	 * @author Alex Luckett <lucketta@aston.ac.uk>
	 */
	private enum DrawSection {
		RIGHT, LEFT
	}
}
