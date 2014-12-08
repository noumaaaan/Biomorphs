package main;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Jurgen Hajdini <hajdinij@aston.ac.uk>
 */

@SuppressWarnings("serial") 
public class BiomorphGUI extends JFrame {
	private JFrame f;

	public BiomorphGUI() {
		//Create the window for the application
		f = new JFrame("Evolutionary Art: PROTOTYPE (STAGE 1)");  
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(800,600));
		f.setResizable(true);
		f.setLocationRelativeTo(null); // centre aligned
		
		//enableOStheme();

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
		f.add(button_panel, BorderLayout.WEST);

		//Defining the Draw Canvas
		final BiomorphPanel d = new BiomorphPanel();		

		f.add(d);
		f.setVisible(true);

		//Action listener to start the application
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				d.revalidate();
				d.repaint();
			}});

		//Action listener to close application
		end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				exit_app();
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

	/**
	 * BiomorphGUI will use native look and feel of host operating system.
	 */
	@SuppressWarnings("unused")
	private void enableOStheme() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException | ClassNotFoundException | 
	    		InstantiationException | IllegalAccessException e) {
	    	// nothing to do here. discard command - will keep standard java theme.
	    }
	}

	public static void main(String[] args){
		new BiomorphGUI(); // display GUI
	}

	private void exit_app()
	{
		int response = JOptionPane.showConfirmDialog(f, "Would you really like to quit the application?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}		
	}


}