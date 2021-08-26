package kiu.oto.common;

import javax.swing.*;

import static kiu.oto.common.CommonMethodsAndSettings.*;

/**
 * Common JFrame (Window) for all sub-programs
 */
public class CommonFrame extends JFrame {

    public CommonFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setFocusable(false);
    }

    /**
     *
     * @param programPanel according panel to certain program
     */
    public void setProgramPanel(CommonPanel programPanel) {
        add(programPanel);
    }


    /**
     * Minimizes the program window
     */
    public void minimize() {
        setState(JFrame.ICONIFIED);
    }

}
