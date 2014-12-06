package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawCanvas extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		
		Biomorph biomorph = new Biomorph();
		biomorph.generateRandomParents();

		g2d.setStroke(new BasicStroke(0.01f));
		g2d.setColor(Color.BLACK);
		
		/// implementation of the drawing just the math's part goes here handling the lines
		// everything will appear on screen
		
		/* keep track of the last x and y coordinates we printed to, so we don't overlap lines */
		int lastX = biomorph.getX() + 500;
		int lastY = biomorph.getY() + 300;
		
		for(Genome genome : biomorph.getGenomes()) {
			double sinAngle = Math.sin((double) genome.getAngle());
			double cosAngle = Math.cos((double) genome.getAngle());
			
			int maxX = (int) (lastX + genome.getLength() * sinAngle);
			int maxY = (int) (lastY + genome.getLength() * cosAngle);
			
			g2d.drawLine(lastX, lastY, maxX, maxY);
			
			lastX = maxX; lastY = maxY;
		}
	}
}
