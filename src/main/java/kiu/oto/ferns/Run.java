package kiu.oto.ferns;

import kiu.oto.common.MyFrame;

import static kiu.oto.ferns.SettingsAndMethods.*;
import static kiu.oto.common.CommonMethodsAndSettings.inputDouble;

public class Run {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            System.out.println("Input value of b1 (default value = " + B1_DEFAULT_VALUE + "):");
            Modifier.setB1(inputDouble());
        } catch (Exception e) {
            System.out.println("Using default value");
        }

        new MyFrame(new Panel());
    }
}
