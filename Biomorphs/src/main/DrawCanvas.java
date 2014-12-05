package main;
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
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		Biomorph b = new Biomorph();
		b.generateRandomParents();
		
		Genome gene = b.getGenomes();
	
		double x = b.getPoint().getX();
		double y = b.getPoint().getY();
		
		g2d.drawLine((int) x, (int) y, (int) x + gene.getLength(), (int) y + gene.getLength());
	}
}
