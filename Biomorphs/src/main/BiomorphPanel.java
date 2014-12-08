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
		
		drawSection(startX, startY, biomorph, true, g2d);
		
		startX = biomorph.getPosition().x + 300;
		startY = biomorph.getPosition().y + 200;
		
		drawSection(startX, startY, biomorph, false, g2d);
	}
	
	private void drawSection(double startX, double startY, Biomorph biomorph, boolean isRightSide, Graphics2D g2d) {
		for(Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());
			
			double sinAngle = Math.sin(genome.getAngle());
			double cosAngle = Math.cos(genome.getAngle());
		
			double endX;
			double endY = startY + (genome.getLength() * cosAngle);
			
			if(isRightSide)
				endX = startX + (genome.getLength() * sinAngle);
			else
				endX = startX - (genome.getLength() * sinAngle);
			
			g2d.draw(new Line2D.Double(startX, startY, endX, endY));
			
			startX = endX; startY = endY;
		}
	}
}
