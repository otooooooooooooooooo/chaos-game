package kiu.oto;

import kiu.oto.common.inputs.ClassCaster;
import kiu.oto.common.inputs.MultiChoiceInputPanel;
import kiu.oto.common.inputs.PopupDialogFrame;

import javax.swing.*;

import static kiu.oto.common.CommonMethodsAndSettings.output;

public class PopupTest {

    public static void main(String[] args) {
      output(  new PopupDialogFrame<String>(new MultiChoiceInputPanel<String>("Title", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"},
                ButtonModel::getActionCommand
        )).getInput());
    }
}
