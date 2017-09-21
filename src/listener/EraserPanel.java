package listener;

import java.awt.BasicStroke;
import client.CommandArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EraserPanel extends JPanel{
	
	public static int width = 1;
	JSlider sizeSlider;
	JPanel jPanel_data;
	
	public EraserPanel() {
		super(new GridLayout(2, 1));
		setBorder(new TitledBorder("EraserSize"));
		setPreferredSize(new Dimension(140, 140));
		
		
		jPanel_data = new JPanel();
		jPanel_data.setLayout(new GridLayout(1, 2));
		sizeSlider=new JSlider();
		jPanel_data.add(sizeSlider);
		jPanel_data.add(CommandArea.rdoEraser);
		
		add(jPanel_data);
		
		sizeSlider.setMaximum(100);
		sizeSlider.setMinimum(width);
		sizeSlider.setValue(width);
		sizeSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				width = sizeSlider.getValue() / 3;
				repaint();
			}
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke(width));
		graphics2d.setColor(Color.GRAY);
		graphics2d.drawLine(30, 110, 170, 110);
		graphics2d.setStroke(new BasicStroke());
	}

}
