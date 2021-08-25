package kiu.oto.AffineTransformation.MainPackage;

import kiu.oto.CommonPackage.MyFrame;

import static kiu.oto.AffineTransformation.MainPackage.SettingsAndMethods.*;
import static kiu.oto.AffineTransformation.MainPackage.Modifier.*;
import static kiu.oto.CommonPackage.CommonMethodsAndSettings.inputDouble;

public class Run {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            System.out.println("Input value of b1 (default value = " + B1_DEFAULT_VALUE + "):");
            setB1(inputDouble());
        } catch (Exception e) {
            System.out.println("Using default value");
        }

        new MyFrame(new Panel());
    }
}
