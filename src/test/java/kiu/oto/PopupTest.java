package kiu.oto;

import kiu.oto.common.inputs.IntegerInputPanel;

import kiu.oto.common.inputs.PopupDialogFrame;

import static kiu.oto.common.CommonMethodsAndSettings.output;

public class PopupTest <T extends  Enum<T>>{

    public static void main(String[] args) {
    PopupDialogFrame<Integer> fr = new PopupDialogFrame<Integer>(new IntegerInputPanel("Corners", 3, 1, 1000));
    output(fr.getInput().toString());
    }


}
