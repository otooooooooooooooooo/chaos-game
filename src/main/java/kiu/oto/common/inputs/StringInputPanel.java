package kiu.oto.common.inputs;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class StringInputPanel extends PopupDialogPanel<String>{
    private final JTextField textField;

    public StringInputPanel(String title, String value) {
        super(title);
        setLayout(null);
        textField = new JTextField(value);
        textField.setSize(getWidth()/10, getHeight()/10);
        textField.setBounds((getWidth() - textField.getWidth())/2, (getHeight() - textField.getHeight()) / 2,
                textField.getWidth(), textField.getHeight());
        add(textField);
        revalidate();
        repaint();
    }

    @Override
    @NotNull
    protected String getSelectedChoice() {
        return textField.getText();
    }
}
