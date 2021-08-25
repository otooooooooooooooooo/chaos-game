package kiu.oto.CommonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
enum for methods and settings that will be used in all packages of this project
 */

public enum CommonMethodsAndSettings {;

private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
//visual settings

    public static final MyColor[] COLOR_CHOICES =
            {
                    new MyColor("RED", Color.red.getRGB()),
                    new MyColor("GREEN", Color.green.getRGB()),
                    new MyColor("BLUE", Color.BLUE.getRGB()),
                    new MyColor("WHITE", Color.white.getRGB()),
                    new MyColor("CYAN", Color.cyan.getRGB()),
                    new MyColor("MAGENTA", Color.MAGENTA.getRGB())
            };



    //PANEL SETTINGS
    public static final int PANEL_WIDTH = SCREEN_DIMENSION.width;
    public static final int PANEL_HEIGHT = SCREEN_DIMENSION.height;
    //frame sometimes hides few pixels of panel, so it needs to be a bit larger
    public static final int FRAME_WIDTH = PANEL_WIDTH + 14;
    public static final int FRAME_HEIGHT = PANEL_HEIGHT + 37;

    //all colors in project are saved as rgb values
    public static int BACKGROUND_COLOR = Color.black.getRGB();
    public static final int DOT_COLOR_1 = Color.cyan.getRGB();


//performance settings
    //iteration count constants
    public static final int AMOUNT_PER_BIG_STEP = 500;
    public static final int AMOUNT_TO_DRAW_FULL_IMAGE = 1000000;

//gallery settings
    public static final int HD_RESOLUTION_WIDTH = 1280;
    public static final int HD_RESOLUTION_HEIGHT = 720;


    public static int EXPORTED_IMAGE_WIDTH = 15360;
    public static int EXPORTED_IMAGE_HEIGHT = 8640;

    //mapping between GUI and internal image
    public static double IMAGE_PANEL_WIDTH_RATIO = EXPORTED_IMAGE_WIDTH / (double) PANEL_WIDTH;
    public static double IMAGE_PANEL_HEIGHT_RATIO = EXPORTED_IMAGE_HEIGHT / (double) PANEL_HEIGHT;

//controls

    public static final int PAINT_NEXT = KeyEvent.VK_SPACE;
    public static final int PAINT_BIG_STEP = KeyEvent.VK_ENTER;
    public static final int PAINT_FULL_IMAGE = KeyEvent.VK_F;
    public static final int EXPORT_IMAGE = KeyEvent.VK_E;
    public static final int MINIMIZE_WINDOW = KeyEvent.VK_ESCAPE;
    public static final int RESTART_PROGRAM = KeyEvent.VK_R;


//methods used commonly in all projects
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //method rounds double to int
    public static int round(double d) {
        return d - (int) d < 0.5 ? (int)d : (int)d + 1;
    }
    //returns distance between two FloatPoints
    public static Double between(FloatPoint a, FloatPoint b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
    //returns middle point of two FloatPoints
    public static FloatPoint midPoint(FloatPoint a, FloatPoint b) {
        return new FloatPoint((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
    }

    private static final String FORMAT_ERROR = "Wrong format. Try again: ";

    public static void setDimensionRatios() {
        IMAGE_PANEL_WIDTH_RATIO = EXPORTED_IMAGE_WIDTH / (double) PANEL_WIDTH;
        IMAGE_PANEL_HEIGHT_RATIO = EXPORTED_IMAGE_HEIGHT / (double) PANEL_HEIGHT;

    }

    public static int inputInteger() {
        try {
            return Integer.parseInt(inputString());
        } catch (NumberFormatException e) {
            System.out.println(FORMAT_ERROR);
            return inputInteger();
        }
    }

    public static double inputDouble() {
        try {
            return Double.parseDouble(inputString());
        } catch (NumberFormatException e) {
            System.out.println(FORMAT_ERROR);
            return inputDouble();
        }
    }

    public static String inputString(){
        try {
            return br.readLine();
        } catch (Exception e) {
            System.out.println(FORMAT_ERROR);
            return inputString();
        }
    }

    public static int inputColor() {
        //outputs all color choices
        Arrays.stream(COLOR_CHOICES).forEach(x -> System.out.println(x.toString()));
        System.out.println("Choose color or input manually: ");
        return inputInteger();
    }

    public static String getChosenExportDirectory() {
        JFileChooser ch = new JFileChooser();
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ch.showOpenDialog(new Container());
        return ch.getSelectedFile().toString();
    }


}
