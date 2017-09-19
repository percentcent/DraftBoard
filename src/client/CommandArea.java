package client;

import listener.ListenForShapeRadio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by hasee on 2017/9/19.
 */
public class CommandArea extends JPanel {
    JPanel pnlShape, pnlColor, pnlEraser;
    //Shape chooser on pnlshape
    public static JRadioButton rdoCircle, rdoOval, rdoLine, rdoRect, rdoFree, rdoText;

    public CommandArea() {
        this.setBorder(new TitledBorder("Commend Area"));
        Dimension d = new Dimension(250, 600);
        this.setPreferredSize(d);
        this.setLayout(new GridLayout(4, 1));

		/*initialize shape chooser panel*/
        pnlShape = new JPanel(new GridLayout(3,2));
        pnlShape.setPreferredSize(new Dimension(240, 140));
        pnlShape.setBorder(new TitledBorder("Shape Types"));

        rdoCircle = new JRadioButton("Circle");
        rdoOval = new JRadioButton("Oval");
        rdoLine = new JRadioButton("Line");
        rdoRect = new JRadioButton("Rectangle");
        rdoFree = new JRadioButton("Freehand",true);
        rdoText = new JRadioButton("Text");

        ButtonGroup grpShape = new ButtonGroup();
        grpShape.add(rdoCircle);
        grpShape.add(rdoOval);
        grpShape.add(rdoLine);
        grpShape.add(rdoRect);
        grpShape.add(rdoFree);
        grpShape.add(rdoText);


        pnlShape.add(rdoCircle);
        pnlShape.add(rdoOval);
        pnlShape.add(rdoLine);
        pnlShape.add(rdoRect);
        pnlShape.add(rdoFree);
        pnlShape.add(rdoText);

        ListenForShapeRadio shapeListener = new ListenForShapeRadio();
        rdoCircle.addItemListener(shapeListener);
        rdoOval.addItemListener(shapeListener);
        rdoLine.addItemListener(shapeListener);
        rdoRect.addItemListener(shapeListener);
        rdoFree.addItemListener(shapeListener);
        rdoText.addItemListener(shapeListener);

        this.add(pnlShape);

    }
}
