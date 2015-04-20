package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;

public class help extends AbstractGUI{
		
super("Biomorph Mutation: Help", 500, 300);
windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
windowFrame.setResizable(false);

JButton close = new JButton("Close");
close.setToolTipText("Close the help screen and return to the beginner menu");

JPanel panel = new JPanel(new GridBagLayout());


// Add the different components to panel
windowFrame.getContentPane().add(panel, BorderLayout.NORTH);
windowFrame.add(panel);	
panel.add(close);

// Set GridBag constraints to position the components on the JFrame
GridBagConstraints c = new GridBagConstraints();
c.gridx = 0;
c.gridy = 0;
c.insets = new Insets(10, 10, 10, 10);
panel.add(close, c);


// Add the listener for the beginner setting
close.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		destroyGui();
		new BasicGUI().displayGui();
	}

});




windowFrame.pack();
windowFrame.setLocationRelativeTo(null); // centre aligned
}

}



