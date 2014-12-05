package main;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class BiomorphGUI {


	public static void main(String[] args){
		JFrame window = new JFrame();
		int empty_space = 5;

		//creating the window frame to display the biomorph
		window = new JFrame("Biomorphs");
		window.setPreferredSize(new Dimension(1000,600));
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
		//creating the buttons
		JButton Quit = new JButton("Quit");
		Quit.setVisible(true);
		JButton Start = new JButton("Start");
		Start.setVisible(true);
		JButton Restart = new JButton("Restart");
		Restart.setVisible(true);
		
		JPanel hold_components = new JPanel();
		hold_components.setVisible(true);
		hold_components.setLayout(new BorderLayout());
		hold_components.setBorder(new EmptyBorder(empty_space, empty_space, empty_space, empty_space));

		JPanel instruction_panel = new JPanel();
		instruction_panel.setVisible(true);
		instruction_panel.setLayout(new BorderLayout());
		instruction_panel.setBorder(new EmptyBorder(empty_space, empty_space, empty_space, empty_space));
		
		
		
		//Create the panel to display the canvas
		JPanel canvasPanel = new JPanel();		

		// adding the buttons to the button panel.
		hold_components.add(Start, BorderLayout.WEST);
		hold_components.add(Restart, BorderLayout.CENTER);
		hold_components.add(Quit, BorderLayout.EAST);
		
		window.getContentPane().add(instruction_panel, BorderLayout.WEST);
		instruction_panel.add(hold_components, BorderLayout.SOUTH);}

	private static JFrame JFrame() {
		return null;

	}

	public class draw extends JFrame {
		
		private DrawCanvas canvas;
		
		public static final int CANVAS_WIDTH = 999;
		public static final int CANVAS_HEIGHT = 400;
		//public static final Color LINE_COLOR = Color.BLACK;
		private int x1 = CANVAS_WIDTH / 2;
		private int y1 = CANVAS_HEIGHT / 8;
		private int x2 = x1;
		private int y2 = CANVAS_HEIGHT / 8 * 7;

	}
	

}
