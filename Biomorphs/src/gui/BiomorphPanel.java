package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
	private Point2D mousePoint;

	private ArrayList<Line2D> lines;

	private Line2D activeLine; // line currently clicked
	private int activeLineNumber; // corresponds to the child genome number

	public BiomorphPanel() {
		this(new EvolutionaryBiomorph(), true); // default use evolutionary
	}

	public BiomorphPanel(final AbstractBiomorph biomorph,
			boolean generateChildren) {
		super();
		this.setBackground(Color.WHITE);

		this.biomorph = biomorph;

		if (generateChildren)
			this.biomorph.generateChildren();

		this.midPoint = new Point2D.Double(0, 0);
		this.mousePoint = new Point2D.Double(0, 0);

		this.lines = new ArrayList<Line2D>(400);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int lineNumber = 0;
				
				for (Line2D line : lines) {
					if (lineNumber == lines.size() / 2) {
						lineNumber = 0; // next half is the right side
					}

					if (isLineClicked(e, line)) {
						activeLine = line;
						biomorph.getGenome().get(lineNumber).setColour(Color.BLACK);
						
						activeLineNumber = lineNumber;

						BiomorphPanel.this.refresh();
					}

					lineNumber++;
				}
			}

			private boolean isLineClicked(MouseEvent event, Line2D line) {
				return line.intersects(event.getX(), event.getY(), 5, 5);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mousePoint.setLocation(e.getX(), e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				checkHit(e, BiomorphPanel.this.lines);
			}
			
			/* e   (lenB)
			 *   -------- active P2
			 *    \     |
			 *     \    |
			 * (len \   | (lenA)
			 *   C)  \  |
			 *        \X|
			 *         \| active P1
			 *         
			 *  lenA = Math.sqrt(p2.x - p1.x)^2 + (p2.y - p1.y)^2)
			 *  lenB = Math.sqrt(e.x - pt2.x)^2 + (e.y - pt2.y)^2)
			 *  lenC = Math.sqrt(e.x - p1.x)^2 + (e.y - p1.y)^2)
			 *  
			 *  cosX = (lenA^2 + lenC^2 - lenB^2) / 2 * lenA * lenC
			 *     X = cos-1(cosX)
			 */

			private void checkHit(MouseEvent e, List<Line2D> lines) {
				Genome genome = biomorph.getGenome().get(activeLineNumber);

				setLength(e, genome);
				setAngle(e, genome);
				
				BiomorphPanel.this.refresh();
			}
			
			private void setAngle(MouseEvent e, Genome genome) {
				int lengthA = (int) calculateLength(activeLine.getX1(), e.getX(),  activeLine.getX2(), e.getY());
				int lengthB = (int) calculateLength(e.getX(), activeLine.getX2(), e.getY(), activeLine.getY2());
				int lengthC = (int) calculateLength(e.getX(), activeLine.getX1(), e.getY(), activeLine.getY1());
				
				double cosX = (Math.pow(lengthA, 2) + Math.pow(lengthC, 2) - Math.pow(lengthB, 2))
								/ (2 * lengthA * lengthC);
				
				double angle = Math.acos(cosX);
				
				if(angle < 0) {
					angle += 360;
				}
				
				genome.setAngle(angle);
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

		});
	}

	private void resetMidPoint() {
		double middleX = super.getSize().getWidth() / 2;
		double middleY = super.getSize().getHeight() / 2;

		midPoint.setLocation(middleX, middleY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // do not remove this - clears the previous
									// canvas

		resetMidPoint();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5f));

		lines.clear();

		lines.addAll(drawSection(midPoint, biomorph, CanvasSide.LEFT, g2d));
		lines.addAll(drawSection(midPoint, biomorph, CanvasSide.RIGHT, g2d));
	}

	/**
	 * Draws a biomorph on one side of the panel.
	 * 
	 * @param startX
	 *            start position on x axis
	 * @param startY
	 *            start position on y axis
	 * @param biomorph
	 *            biomorph to draw
	 * @param g2d
	 *            graphics to draw with
	 */
	private ArrayList<Line2D> drawSection(Point2D point,
			AbstractBiomorph biomorph, CanvasSide section, Graphics2D g2d) {
		ArrayList<Line2D> lines = new ArrayList<Line2D>(200);

		double lastX = point.getX();
		double lastY = point.getY();

		for (Genome genome : biomorph.getGenome()) {
			g2d.setColor(genome.getColour());

			double sinAngle = Math.sin(genome.getAngle()); // used to calculate end point on X axis
			double cosAngle = Math.cos(genome.getAngle()); // used to calculate end point on Y axis

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
		biomorph.getGenome().get(activeLineNumber).setColour(Color.PINK);		
	}

	/**
	 * Represents the section of a panel to draw on.
	 * 
	 * @author Alex Luckett <lucketta@aston.ac.uk>
	 */
	private enum CanvasSide {
		RIGHT, LEFT
	}
}
