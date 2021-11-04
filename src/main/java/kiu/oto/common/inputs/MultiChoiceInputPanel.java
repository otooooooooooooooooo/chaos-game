package kiu.oto.common.inputs;

import javax.swing.*;

public class MultiChoiceInputPanel<Input> extends PopupDialogPanel<Input>{
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final MultiChoiceHandler<Input> handler;

    public MultiChoiceInputPanel(String title, MultiChoiceHandler<Input> handler) {
        super(title);
        this.handler = handler;
        String[] texts = handler.getTexts();
        int length = texts.length;

        String[] actionCommands = handler.getActionCommands();
        JRadioButton[] checkboxes = new JRadioButton[length];
        for(int i = 0; i < length; i++) {
            checkboxes[i] = new JRadioButton(texts[i]);
            checkboxes[i].setActionCommand(actionCommands[i]);
            buttonGroup.add(checkboxes[i]);
            add(checkboxes[i]);
        }
        buttonGroup.setSelected(checkboxes[0].getModel(), true);
        revalidate();
        repaint();
    }

    @Override
    protected Input getSelectedChoice() {
        return handler.getType(buttonGroup.getSelection());
    }
}
