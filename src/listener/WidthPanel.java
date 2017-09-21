package listener;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WidthPanel extends JPanel {
	
	public static int width = 1;
	JSlider widthSlider;
	JTextField widthText;
	JPanel jPanel_data;

	public WidthPanel() {
		super(new GridLayout(2, 1));
		setBorder(new TitledBorder("LineWidth"));
		setPreferredSize(new Dimension(140, 140));
		
		widthSlider = new JSlider();
		jPanel_data = new JPanel();
		widthText = new JTextField();

		jPanel_data.setLayout(new GridLayout(1, 2));

		jPanel_data.add(widthSlider);
		jPanel_data.add(widthText);

		add(jPanel_data);

		widthSlider.setMaximum(100);
		widthSlider.setMinimum(width);
		widthSlider.setValue(width);
		widthSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				width = widthSlider.getValue() / 3;
				widthText.setText("" + (width*3+1));
				repaint();
				//DragDrawPanel.width = width;
			}
		});

		widthText.setText("" + width);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke(width));
		//graphics2d.setColor(ColorHandler.color);
		graphics2d.drawLine(30, 110, 170, 110);
		graphics2d.setStroke(new BasicStroke());
	}

}
