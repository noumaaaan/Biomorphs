package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * A panel to draw a biomorph. 
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class BiomorphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // do not remove this - clears the previous canvas
		
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.WHITE);
		
		Biomorph biomorph = new Biomorph();
		biomorph.generateRandomParents();
		
		double startX = biomorph.getPosition().x + 300;
		double startY = biomorph.getPosition().y + 200;
		
		drawSection(startX, startY, biomorph, true, g2d);		
		drawSection(startX, startY, biomorph, false, g2d);
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
	private void drawSection(double startX, double startY, Biomorph biomorph, boolean isRightSide, Graphics2D g2d) {
		double lastX = new Double(startX);
		double lastY = new Double(startY);
		
		for(Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());
		
			double sinAngle = Math.sin(genome.getAngle()); // used to calculate end point on X axis
			double cosAngle = Math.cos(genome.getAngle()); // used to calculate end point on Y axis
		
			double endX = 0;
			double endY = lastY + (genome.getLength() * cosAngle); // always the same -> want it symmetrical along y axis
			
			if(isRightSide) // need to invert if left side (+ and -)
				endX = lastX + (genome.getLength() * sinAngle);
			else
				endX = lastX - (genome.getLength() * sinAngle);
			
			g2d.draw(new Line2D.Double(lastX, lastY, endX, endY)); // draw the line
			
			lastX = endX; lastY = endY; // update start position for next line to use
		}
	}
}
