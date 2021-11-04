package kiu.oto.common.inputs;

import javax.swing.*;
import java.awt.*;

public class ColorInputPanel extends PopupDialogPanel<Color>{
    JColorChooser cc;
    public ColorInputPanel(String title) {
        super(title);
        cc = new JColorChooser();
        cc.setSize(500, 300);
        add(cc);
        revalidate();
        repaint();
    }

    @Override
    protected Color getSelectedChoice() {
        return cc.getColor();
    }
}
