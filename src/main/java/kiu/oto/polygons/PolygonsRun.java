package kiu.oto.polygons;

import kiu.oto.common.CommonFrame;

import static kiu.oto.polygons.PolygonsSettingsAndMethods.*;
import static kiu.oto.common.CommonMethodsAndSettings.inputInteger;

public class PolygonsRun {
    public static void main(String[] args) {
         run();
    }

    public static void run() {

        try {
            System.out.println("Input polygon corner count (4, 5, 6, 7 recommended):");
            int input = inputInteger();
            if(input < 1)
                throw new Exception();
            PolygonsModifier.setPolygonCornerCount(input);
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
            PolygonsModifier.setRuleSet(input);
        } catch (Exception e) {
            System.out.println("Using default rule set: " + PolygonsModifier.getRuleSet());
        }


        new CommonFrame(new PolygonsPanel());
    }
}
