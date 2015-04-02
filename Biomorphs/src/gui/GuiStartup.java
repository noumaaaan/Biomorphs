package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Display splash screen with user selection.
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */
public class GuiStartup extends AbstractGUI {

	public static void main(String[] args) {
		if (args.length == 0) {
			new GuiStartup().displayGui();
		} else {
			switch (args[0]) {
				case "evolutionary":
					new EvolutionaryGUI().displayGui();
					break;
				case "basic":
					new BasicGUI().displayGui();
					break;
				default:
					new GuiStartup().displayGui();
			}
		}
	}

	public GuiStartup() {
		/**
		 * First part concerns choosing user type The user has the choice to
		 * either be a beginner or advanced user
		 */

		// Create the window frame where ut = user type
		windowFrame = new JFrame("Choose User Type");
		windowFrame.setSize(500, 300);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);

		// Create components the different components
		JLabel description = new JLabel("Nouman is our KING");
		JLabel usertype = new JLabel("Choose the type of user you are: ");
		JButton beginner = new JButton("Beginner");
		beginner.setToolTipText("If you're using the application for the first time, click here");
		JButton advanced = new JButton("Advanced");
		advanced.setToolTipText("Click here if you'd like to experience the 5th dimension!");
		JPanel panel = new JPanel(new GridBagLayout());

		// Add components to panel
		windowFrame.getContentPane().add(panel, BorderLayout.NORTH);
		windowFrame.add(panel);
		panel.add(description);
		panel.add(usertype);
		panel.add(beginner);
		panel.add(advanced);

		// Set GridBag constraints
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(description, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usertype, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(beginner, c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(advanced, c);

		// Add the listener for the beginner and advanced
		beginner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				new BasicGUI().displayGui();
			}

		});

		// Add the listener for the beginner and advanced
		advanced.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				new EvolutionaryGUI().displayGui();
			}

		});

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}
}