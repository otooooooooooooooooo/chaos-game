package kiu.oto.ChaosGamePolygons.MainPackage;

import kiu.oto.CommonPackage.MyFrame;

import static kiu.oto.ChaosGamePolygons.MainPackage.SettingsAndMethods.*;
import static kiu.oto.ChaosGamePolygons.MainPackage.Modifier.*;
import static kiu.oto.CommonPackage.CommonMethodsAndSettings.inputInteger;

public class Run {
    public static void main(String[] args) {
         run();
    }

    public static void run() {

        try {
            System.out.println("Input polygon corner count (4, 5, 6, 7 recommended):");
            int input = inputInteger();
            if(input < 1)
                throw new Exception();
            setPolygonCornerCount(input);
        } catch (Exception e) {
            System.out.println("Using default corner count: " + DEFAULT_POLYGON_CORNER_COUNT + '\n');
        }

        try {
            for (Rule x : rules) {
                System.out.println(x.toString());
            }
            System.out.println("Input index of rule set:");
            int input = inputInteger();
            if(input < 0 || input >= rules.size())
                throw new Exception();
            setRuleSet(input);
        } catch (Exception e) {
            System.out.println("Using default rule set: " + getRuleSet());
        }


        new MyFrame(new Panel());
    }
}
