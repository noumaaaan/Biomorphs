package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biomorph.AbstractBiomorph;
import biomorph.RandomBiomorph;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */
public class BasicGUI extends AbstractGUI {

	public BasicGUI() {
		super();
		
		//Create the window for the application
		windowFrame = new JFrame("Evolutionary Art: PROTOTYPE (STAGE 1)");  
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setPreferredSize(new Dimension(800,600));
		windowFrame.setResizable(true);

		//Create the panel to hold the buttons and define Grid Layout
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(4, 1, 20, 20));

		//Create the buttons for the panel 
		JButton start = new JButton(" New Biomorph ");
		start.setToolTipText("Create a new biomorph by clicking here"); 
		JButton load = new JButton(" Load ");
		load.setToolTipText("Open up a previously saved biomorph mutation");
		load.setEnabled(false); // TODO: re-enable this when feature implemented
		JButton help = new JButton(" Help ");
		help.setToolTipText("Click here for Instructions on how to use the application");
		help.setEnabled(false); // TODO: re-enable this when feature implemented
		JButton end = new JButton(" Exit ");
		end.setToolTipText("Quit the application by clicking here");

		//Place the buttons within the panel and add them to the frame
		button_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_panel.add(start);
		button_panel.add(load);
		button_panel.add(help);
		button_panel.add(end);
		windowFrame.add(button_panel, BorderLayout.WEST);

		//Defining the Draw Canvas
		AbstractBiomorph bio = new RandomBiomorph();
		final BiomorphPanel panel = new BiomorphPanel(bio, true);

		windowFrame.add(panel);
		windowFrame.setVisible(true);

		//Action listener to start the application
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				panel.getBiomorph().mutate();
				panel.refresh();
			}
		});

		//Action listener to close application
		end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				exitApplication(windowFrame);
			}
		});

		windowFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event){
				exitApplication(windowFrame);
			}
		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

}