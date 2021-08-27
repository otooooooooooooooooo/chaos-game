package kiu.oto.common.inputs;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static kiu.oto.common.CommonMethodsAndSettings.POPUP_PANEL_DIMENSION;
import static kiu.oto.common.CommonMethodsAndSettings.br;

public abstract class PopupDialogPanel<Input> extends JPanel {
    private Input result;

    private final JButton button;

    @Setter
    private PopupDialogFrame<Input> frame;

    @Getter
    private final String title;

    public PopupDialogPanel(String title) {
        this.title = title;
        this.setBorder(new TitledBorder(new EtchedBorder()));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(POPUP_PANEL_DIMENSION);
        setBounds(0, 0, getWidth(), getHeight());


        button = new JButton("Submit");
        ActionListener ac = e -> {
            result = getSelectedChoice();
            synchronized (button) {
                button.notify();
            }
        };
        button.addActionListener(ac);
        button.setSize(100, 50);
        add(button);
        revalidate();
        repaint();


    }

    protected abstract Input getSelectedChoice();

    public Input getChoice() {
        synchronized (button) {
            try {
                button.wait();
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
        frame.dispose();
        return result;
    }

}

