package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.EvolutionaryBiomorph;
import model.FileSerializer;
import model.Genome;

public class HallOfFameGUI extends AbstractGUI {
	
	private int biomorphToDelete;
	private int biomorphToLoad;
	
	private List<JButton> deleteButtons;
	private List<JButton> loadButtons;

	public static final String HALL_OF_FAME_SUBDIRECTORY = "/hallOfFame";

	public HallOfFameGUI() {
		super("Biomorph Hall of Fame", 800, 450);
		biomorphToDelete = -1;
		
		deleteButtons = new ArrayList<JButton>();
		loadButtons = new ArrayList<JButton>();

		JPanel biomorphContainer = new JPanel();

		String currentPath = System.getProperty("user.dir"); // get current directory

		loadBiomorphs(biomorphContainer, currentPath);

		windowFrame.add(biomorphContainer);

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	private void loadBiomorphs(JPanel container, String currentPath) {
		try {
			for(final File file : new File(currentPath + HALL_OF_FAME_SUBDIRECTORY).listFiles()) {
				try {
					Genome genome = new FileSerializer<Genome>().deserialiseFile(file.getAbsolutePath());
	
					EvolutionaryBiomorph biomorph = new EvolutionaryBiomorph(genome);
	
					BiomorphPanel biomorphPanel = new BiomorphPanel(biomorph, false);
					JButton loadBtn = new JButton("Load");
					JButton deleteBtn = new JButton("Delete");
					
					String fileName = file.getName();
					
					final int fileNumber = Integer.parseInt(fileName.replaceAll("biomorph", ""));
					
					deleteBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							biomorphToDelete = fileNumber;
						}
					});
					
					deleteButtons.add(deleteBtn);
					
					loadBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							biomorphToLoad = fileNumber;
						}
					});
					
					deleteButtons.add(loadBtn);
					
					JPanel buttons = new JPanel();
					buttons.add(loadBtn);
					buttons.add(deleteBtn);
					
					JPanel entryContainer = new JPanel(new BorderLayout());
					
					entryContainer.add(biomorphPanel, BorderLayout.CENTER);
					entryContainer.add(buttons, BorderLayout.SOUTH);
					
					biomorphPanel.setPreferredSize(new Dimension(150, 150));
					
					container.add(entryContainer);
					
				} catch (IOException | ClassNotFoundException e) {
					String message = "Unable to load file: " + file.getAbsolutePath();

					JOptionPane.showMessageDialog(windowFrame, message, "Unable to load biomorph", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (Exception e) {
			String message = "No biomorphs exist within the hall of fame";

			JOptionPane.showMessageDialog(windowFrame, message, "Unable to load biomorph", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public int getNumberBiomorphToDelete() {
		return biomorphToDelete;
	}
	
	public int getNumberBiomorphToLoad() {
		return biomorphToLoad;
	}
	
	public void addDeleteHallOfFameBiomorph(ActionListener listener) {
		for(JButton button : deleteButtons) {
			button.addActionListener(listener);
		}
	}
	
	public void addLoadHallOfFameBiomorph(ActionListener listener) {
		for(JButton button : loadButtons) {
			button.addActionListener(listener);
		}
	}
}
