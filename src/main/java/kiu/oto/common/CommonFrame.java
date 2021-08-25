package kiu.oto.common;

import javax.swing.*;
import java.awt.*;

import static kiu.oto.common.CommonMethodsAndSettings.*;

//Common frame for all projects.
public class CommonFrame extends JFrame {
    public CommonFrame(CommonPanel panel) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        add(panel);
        panel.updateImage();
        setVisible(true);
    }

    public void minimize() {
        setState(JFrame.ICONIFIED);
    }

    @Override
    public Component add(Component component) {
        if(component instanceof CommonPanel)
            ((CommonPanel) component).setFrame(this);
        return super.add(component);
    }
}
