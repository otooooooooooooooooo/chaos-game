package kiu.oto.common.inputs;

import javax.swing.*;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public class PopupDialogFrame<Input> extends JFrame {
    private static final int startPointScale = POPUP_PANEL_DIMENSION_DOWNSCALE * 2;
    private final PopupDialogPanel<Input> panel;

    public PopupDialogFrame(PopupDialogPanel<Input> panel) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(POPUP_FRAME_DIMENSION);
        setBounds(PANEL_WIDTH / startPointScale + 7,
                PANEL_HEIGHT / startPointScale, getWidth(), getHeight());


        this.panel = panel;
        add(panel);
        setVisible(true);

    }

    public Input getInput() {
        return panel.getChoice();
    }

}




