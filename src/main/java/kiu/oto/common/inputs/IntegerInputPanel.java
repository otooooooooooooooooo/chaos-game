package kiu.oto.common.inputs;

import javax.swing.*;

public class IntegerInputPanel extends PopupDialogPanel<Integer> {

    private final JSpinner spinner;

    public IntegerInputPanel(String title, int value, int minimum, int maximum) {
        super(title);
        setLayout(null);
        spinner = new JSpinner(new SpinnerNumberModel(value, minimum, maximum, 1));
        spinner.setSize(getWidth() / 10, getHeight() / 10);
        spinner.setBounds((getWidth() - spinner.getWidth())/2, (getHeight() - spinner.getHeight()) / 2, spinner.getWidth(), spinner.getHeight());
        add(spinner);
        revalidate();
        repaint();
    }

    @Override
    protected Integer getSelectedChoice() {
        return (Integer)spinner.getModel().getValue();
    }
}
