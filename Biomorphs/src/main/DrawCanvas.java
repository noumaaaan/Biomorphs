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
		
		Biomorph b = new Biomorph();
		b.generateRandomParents();
		Genome gene = b.getGenomes().getParent();

		g2d.setColor(Color.BLACK);
		
		
		/// implementation of the drawing just the math's part goes here handling the lines
		// everything will appear on screen
		
		g2d.drawLine(b.getX(), b.getY(), b.getX() +65, b.getY() + 42);
	}
}
