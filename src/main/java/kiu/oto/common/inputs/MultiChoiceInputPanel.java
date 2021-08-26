package kiu.oto.common.inputs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MultiChoiceInputPanel<Input> extends PopupDialogPanel<Input>{
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ClassCaster<ButtonModel, Input> caster;


    public MultiChoiceInputPanel(String title, String[] choices, ClassCaster<ButtonModel, Input> caster) {
        super();
        this.caster = caster;
        this.setBorder(new TitledBorder(new EtchedBorder(), title));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int length = choices.length;
        JRadioButton[] checkboxes = new JRadioButton[length];
        for(int i = 0; i < length; i++) {
            checkboxes[i] = new JRadioButton(choices[i]);
            checkboxes[i].setActionCommand(choices[i]);
            buttonGroup.add(checkboxes[i]);
            add(checkboxes[i]);
        }
        buttonGroup.setSelected(checkboxes[0].getModel(), true);




        revalidate();
        repaint();

    }

    @Override
    protected Input getSelectedChoice() {
        return caster.cast(buttonGroup.getSelection());
    }
}
