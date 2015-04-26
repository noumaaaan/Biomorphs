package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import biomorph.AbstractBiomorph;
import biomorph.EvolutionaryBiomorph;
import biomorph.Genome;

/**
 * A panel to draw a biomorph.
 * 
 * TODO: make all biomorphs scale within the panel.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class BiomorphPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private AbstractBiomorph biomorph;

	private Point2D midPoint;

	private ArrayList<Line2D> leftLines;
	private ArrayList<Line2D> rightLines;

	private Line2D activeLine; // line currently clicked
	private int activeLineNumber; // corresponds to the child genome number

	public BiomorphPanel() {
		this(new EvolutionaryBiomorph(), true); // default use evolutionary
	}

	public BiomorphPanel(final AbstractBiomorph biomorph, boolean generateChildren) {
		super();
		this.setBackground(Color.WHITE);

		this.biomorph = biomorph;

		if (generateChildren)
			this.biomorph.generateChildren();

		midPoint = new Point2D.Double(0, 0);

		leftLines = new ArrayList<Line2D>(400);
		rightLines = new ArrayList<Line2D>(400);

		addMouseListener(new BiomorphMouseHandler());
		addMouseMotionListener(new BiomorphMouseHandler());
	}

	private void resetMidPoint() {
		double middleX = super.getSize().getWidth() / 2;
		double middleY = super.getSize().getHeight() / 2;

		midPoint.setLocation(middleX, middleY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // do not remove this - clears the previous canvas

		resetMidPoint();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5f));

		leftLines.clear();
		rightLines.clear();

		leftLines.addAll(drawSection(midPoint, biomorph, CanvasSide.LEFT, g2d));
		rightLines.addAll(drawSection(midPoint, biomorph, CanvasSide.RIGHT, g2d));
	}

	/**
	 * Draws a biomorph on one side of the panel.
	 * 
	 * @param startX start position on x axis
	 * @param startY start position on y axis
	 * @param biomorph biomorph to draw
	 * @param g2d graphics to draw with
	 */
	private ArrayList<Line2D> drawSection(Point2D point,
			AbstractBiomorph biomorph, CanvasSide section, Graphics2D g2d) {
		ArrayList<Line2D> lines = new ArrayList<Line2D>(200);

		double lastX = point.getX();
		double lastY = point.getY();

		for (Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());

			double sinAngle = Math.sin(genome.getAngle() + 180); // used to calculate end point on X axis
			double cosAngle = Math.cos(genome.getAngle() + 180); // used to calculate end point on Y axis

			double endX = genome.getLength() * sinAngle;
			double endY = lastY + (genome.getLength() * cosAngle); // always the same -> want it symmetrical along y axis
			
			if (section == CanvasSide.RIGHT) { // need to invert if left side (+ and -)
				endX = lastX + endX;
			} else {
				endX = lastX - endX;
			}

			Line2D line = new Line2D.Double(lastX, lastY, endX, endY);
			lines.add(line);
			g2d.draw(line); // draw the line

			lastX = endX;
			lastY = endY; // update start position for next line to use
		}

		return lines;
	}

	public void setBiomorph(AbstractBiomorph biomorph) {
		this.biomorph = biomorph;
	}

	public AbstractBiomorph getBiomorph() {
		return biomorph;
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	public void clearActiveLine() {
		activeLine = null;
		activeLineNumber = 0;	
	}

	/**
	 * Represents the section of a panel to draw on.
	 * 
	 * @author Alex Luckett <lucketta@aston.ac.uk>
	 */
	private enum CanvasSide {
		RIGHT, LEFT
	}
	
	private class BiomorphMouseHandler extends MouseAdapter {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(!checkSide(leftLines, e)) { // only check against right hand side if not on left. no need to pointlessly check.
				checkSide(rightLines, e);
			}
		}
		
		private boolean checkSide(List<Line2D> lines, MouseEvent e) {
			biomorph.getGenome().get(activeLineNumber).setHighlighted(false); // clear previous selection
			
			boolean isLineFound = false;
			
			int lineNumber = 0;
			
			for (Line2D line : lines) {
				if (isLineClicked(e, line)) {
					activeLine = line;
					biomorph.getGenome().get(lineNumber).setHighlighted(true);
					
					activeLineNumber = lineNumber;
					
					isLineFound = true;
					BiomorphPanel.this.refresh();
				}

				lineNumber++;
			}
			
			return isLineFound;
		}
		
		private boolean isLineClicked(MouseEvent event, Line2D line) {
			return line.intersects(event.getX(), event.getY(), 5, 5); // 5 is padding around line to allow for easy clicking
		}
		
		@Override
        public void mouseDragged(MouseEvent e) {
			Genome genome = biomorph.getGenome().get(activeLineNumber);

			setLength(e, genome);
			setAngle(e, genome);
			
			BiomorphPanel.this.refresh();
		}
		
		private void setAngle(MouseEvent e, Genome genome) {
			double angle = getAngleBetweenTwoPoints(activeLine.getP1(), e.getPoint(), activeLine.getP2());
			
			genome.setAngle(angle);
		}
		
		private double getAngleBetweenTwoPoints(Point2D point1, Point2D point2, Point2D fixedPoint) {
			double angle1 = Math.atan2(point1.getY() - fixedPoint.getY(), point1.getX() - fixedPoint.getX());
			double angle2 = Math.atan2(point2.getY() - fixedPoint.getY(), point2.getX() - fixedPoint.getX());
			
			return (angle1 - angle2);
		}
		
		private void setLength(MouseEvent e, Genome genome) {
			/* starts at the starting point of the current line */
			double startX = activeLine.getX1();
			double startY = activeLine.getY1();

			/* ends where the user's cursor currently is (drag location) */
			double endX = e.getX();
			double endY = e.getY();
			
			int length = (int) calculateLength(startX, endX, startY, endY);
			
			genome.setLength(length);
		}
		
		private double calculateLength(double x1, double y1, double x2, double y2 ) {
			return Math.sqrt(
					Math.pow(x2 - x1, 2) + 
					Math.pow(y2 - y1, 2)
				);
		}

	}

}
