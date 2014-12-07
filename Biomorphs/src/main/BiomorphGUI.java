package main;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 */

@SuppressWarnings("serial") // TODO handle this better
public class BiomorphGUI extends JFrame{
	private static JFrame f;

	public static void main(String[] args){

		//Creates the main window via JFrame; Sets size, visibility, colour and close operation
		f = new JFrame("Biomorph Mutation");  
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(600,400));
		f.pack();	
		f.setResizable(false);
		f.setVisible(true); 

		//Create the buttons 
		JButton start = new JButton("START");
		start.setToolTipText("Start application by clicking here"); 

		JButton end = new JButton("END");
		end.setToolTipText("Quit the application by clicking here");

		//Create a panel for the buttons, add the buttons and position it SOUTH of fram 
		JPanel button_panel = new JPanel();
		button_panel.add(start, BorderLayout.EAST);
		button_panel.add(end, BorderLayout.WEST);
		f.add(button_panel, BorderLayout.SOUTH);

		//Set Visibility of the buttons
		start.setVisible(true);
		//restart.setVisible(true);
		end.setVisible(true);

		//Add the canvas to the JFrame 
		final DrawCanvas d = new DrawCanvas();
		f.add(d);

		//Action listener to close application
		end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				exit_app();
			}});

		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				d.revalidate();
				d.repaint();
			}});

		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				exit_app();
			}
		});

		f.pack();
		f.setVisible(true);
	}

	private static void exit_app()
	{
		int response = JOptionPane.showConfirmDialog(f, "Would you really like to quit the application?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}

	}
}