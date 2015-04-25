package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JFrame{

	JSlider redSlider, blueSlider, greenSlider;
	JLabel redLabel, blueLabel, greenLabel;
	JPanel colorPanel, labelPanel, sliders;
	
	public Slider(){
		
		/** Construct the different sliders */
		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		
		/** Construct the different labels */
		redLabel = new JLabel(" Red : 0 ");
		blueLabel = new JLabel(" Green : 0 ");
		greenLabel = new JLabel(" Blue : 0 ");
	
		event e = new event();
		redSlider.addChangeListener(e);
		blueSlider.addChangeListener(e);
		greenSlider.addChangeListener(e);
		
		colorPanel = new JPanel();
		colorPanel.setBackground(Color.black);
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(1, 3, 3, 3));
		
		sliders	= new JPanel();
		labelPanel = new JPanel();
		
		pane.add(sliders);
		pane.add(labelPanel);
		pane.add(colorPanel);
		
		sliders.setLayout(new GridLayout(3, 1, 3, 3));
		sliders.add(redSlider);
		sliders.add(blueSlider);
		sliders.add(greenSlider);
		
		labelPanel.setLayout(new GridLayout(3, 1, 2, 2));
		labelPanel.add(redLabel);
		labelPanel.add(blueLabel);
		labelPanel.add(greenLabel);
	}
	
	
	public class event implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red " + r);
			blueLabel.setText("Blue " + b);
			greenLabel.setText("Green " + g);
			
			colorPanel.setBackground(new Color(r,g,b));
			
		}
		
	}	
	
	public static void main(String args[]){
		
		Slider gui = new Slider();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setTitle("test");
		gui.setSize(300, 110);
		
	}
		
	

}

