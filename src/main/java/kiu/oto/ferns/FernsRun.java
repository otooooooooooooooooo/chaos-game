package kiu.oto.ferns;

import kiu.oto.common.CommonFrame;

import static kiu.oto.ferns.FernsSettingsAndMethods.*;
import static kiu.oto.common.CommonMethodsAndSettings.inputDouble;

public class FernsRun {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            System.out.println("Input value of b1 (default value = " + B1_DEFAULT_VALUE + "):");
            FernsModifier.setB1(inputDouble());
        } catch (Exception e) {
            System.out.println("Using default value");
        }

        new CommonFrame(new FernsPanel());
    }
}
