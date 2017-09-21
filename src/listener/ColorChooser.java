package listener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ColorChooser extends JPanel{
	JColorChooser jColorChooser;
	JButton chooseColorBtn;
	Color tempColor;
	public static Color color;
	JPanel showColorPnl;
	
	public ColorChooser() {
		super(new GridLayout(1, 2));
		showColorPnl = new JPanel();
		color = new Color(0, 0, 0);
		setBorder(new TitledBorder("ColorChooser"));
		showColorPnl.setBackground(color);
		setPreferredSize(new Dimension(100, 140));
		chooseColorBtn = new JButton();
		jColorChooser = new JColorChooser();
		chooseColorBtn.setText("Open the Color Mannger");
		chooseColorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//tempColor is the color the user choose in color manager
				tempColor = JColorChooser.showDialog(new JFrame(), "ColorChooser", color.BLACK);
				if (tempColor != null) {
					color = tempColor;
					//DrawShape.lineWidthPanel.repaint();
				}
				showColorPnl.setBackground(color);
			}

		});

		add(chooseColorBtn);
		add(showColorPnl);
	}

}
