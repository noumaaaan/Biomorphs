package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AbstractBiomorph;

public class SaveAs extends AbstractGUI {


		public SaveAs() {
			super("", 120, 120);
			//windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowFrame.setResizable(false);
			windowFrame.pack();
			windowFrame.setLocationRelativeTo(null); // centre aligned

			
			JButton savejpeg = new JButton("Save as JPEG");
			savejpeg.setToolTipText("Save the Biomorph as a JPEG file");
			
			JButton savefile = new JButton("Save as Project");
			savefile.setToolTipText("Save the project so you can work on it later");
			
			JButton returnScreen = new JButton(" Return ");
			returnScreen.setToolTipText("Return to the Biomorph Mutation Screen");
							
			JPanel panel = new JPanel();
			panel.add(savejpeg);
			panel.add(savefile);
			panel.add(returnScreen);
			
			windowFrame.add(panel);

	
	
	
	
}

		@Override
		public void addGenerateListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addMutateListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addUpdateBiomorphListener(MouseListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addExitListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addSaveListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addLoadListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addHelpListener(ActionListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateMutations(List<AbstractBiomorph> biomorphs) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public AbstractBiomorph getMutatedBiomorph() {
			// TODO Auto-generated method stub
			return null;
		}
}