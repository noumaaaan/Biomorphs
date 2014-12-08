package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JPanel;

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
		//g2d.setStroke(new BasicStroke(2));
		
		Biomorph biomorph = new Biomorph();
		biomorph.generateRandomParents();
		
		/// implementation of the drawing just the math's part goes here handling the lines
		// everything will appear on screen
		
		/* keep track of the last x and y coordinates we printed to, so we don't overlap lines */
		double startX = biomorph.getPosition().x + 300;
		double startY = biomorph.getPosition().y + 200;
		
		for(Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());
			
			double sinAngle = Math.sin(genome.getAngle());
			double cosAngle = Math.cos(genome.getAngle());
			
			double endX = startX + (genome.getLength() * sinAngle);
			double endY = startY + (genome.getLength() * cosAngle);
			
			g2d.draw(new Line2D.Double(startX, startY, endX, endY));
			
			startX = endX; startY = endY;
		}
		
		// LEFT SIDE
		
		/* keep track of the last x and y coordinates we printed to, so we don't overlap lines */
		startX = biomorph.getPosition().x + 300;
		startY = biomorph.getPosition().y + 200;
		
		for(Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());
			
			double sinAngle = Math.sin(genome.getAngle());
			double cosAngle = Math.cos(genome.getAngle());
			
			double endX = startX - (genome.getLength() * sinAngle);
			double endY = startY + (genome.getLength() * cosAngle);
			g2d.draw(new Line2D.Double(startX, startY, endX, endY));
			
			startX = endX; startY = endY;
		}
	}
}
