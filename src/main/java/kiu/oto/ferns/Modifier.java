package kiu.oto.ferns;

import kiu.oto.common.AbstractModifier;
import kiu.oto.common.FloatPoint;

import java.util.ArrayList;
import java.util.List;

import static kiu.oto.ferns.SettingsAndMethods.B1_DEFAULT_VALUE;

public class Modifier extends AbstractModifier {
    private static final List<Integer> odds = new ArrayList<>();
    private static final int prob0 = 1;
    private static final int prob1 = 85;
    private static final int prob2 = 7;
    private static final int prob3 = 7;
    private static double b1 = B1_DEFAULT_VALUE;


    public static void setB1(double d) {
        b1 = d;
    }

    @Override
    //sets probabilities of different transformations
    public void prepareSetup() {
        for(int i = 0; i < prob0; i++)
            odds.add(0);
        for(int i = 0; i < prob1; i++)
            odds.add(1);
        for(int i = 0; i < prob2; i++)
            odds.add(2);
        for(int i = 0; i < prob3; i++)
            odds.add(3);
    }
   @Override
    public void modify(FloatPoint point) {
        double x = point.getX();
        double y = point.getY();
        int n = odds.get(RANDOM.nextInt(odds.size()));

        //Remove comment for colorful image
        //    point.setColor(RANDOM.nextInt());

        //some nerd stuff
        double tempX = a(n)*x + b(n)*y;
        double tempY = c(n)*x + d(n)*y + f(n);
        point.setX(tempX);
        point.setY(tempY);
    }

    private static double a(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1 -> 0.85;
            case 2 -> 0.2;
            case 3 -> -0.15;
            default -> -1;
        };
    }

    private static double b(int n) {
        return switch (n) {
            case 0 -> 0;
            //b1 value determines how curvy a fern is. default value is usually 0.04
            case 1 -> b1;
            case 2 -> -0.26;
            case 3 -> 0.28;
            default -> -1;
        };
    }

    private static double c(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1 -> -0.04;
            case 2 -> 0.23;
            case 3 -> 0.26;
            default -> -1;
        };
    }

    private static double d(int n) {
        return switch (n) {
            case 0 -> 0.16;
            case 1 -> 0.85;
            case 2 -> 0.22;
            case 3 -> 0.24;
            default -> -1;
        };
    }

    private static double f(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1, 2 -> 1.6;
            case 3 -> 0.44;
            default -> -1;
        };
    }
}
