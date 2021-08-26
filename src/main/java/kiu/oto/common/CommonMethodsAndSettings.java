package kiu.oto.common;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

//TODO pull settings from outer sources, some fields can be private

/**
 * enum for methods and settings that will be used in all packages of this project
 */

public enum CommonMethodsAndSettings {;

    /**
     * Initializes dimension with screen size of current user
     */
    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();


//visual settings

    //TODO delete after GUI color chooser refactoring
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


    public static final int POPUP_PANEL_DIMENSION_DOWNSCALE = 2;


    public static final Dimension POPUP_PANEL_DIMENSION =
            new Dimension(PANEL_WIDTH / POPUP_PANEL_DIMENSION_DOWNSCALE,
                        PANEL_HEIGHT / POPUP_PANEL_DIMENSION_DOWNSCALE);
    public static final Dimension POPUP_FRAME_DIMENSION =
            new Dimension(POPUP_PANEL_DIMENSION.width + 14,
                    POPUP_PANEL_DIMENSION.height + 37);

    //TODO this is bs needs refactor
    //all colors in project are saved as rgb values
    public static int BACKGROUND_COLOR = Color.black.getRGB();
    public static final int DOT_COLOR_1 = Color.cyan.getRGB();

    /**
     * Random object to be used in modifier classes
     */
    public static final Random RANDOM= new Random();


//performance settings //TODO WTF
    //iteration count constants
    public static final int AMOUNT_PER_BIG_STEP = 500;
    public static final int AMOUNT_TO_DRAW_FULL_IMAGE = 1000000;

//gallery settings
    public static final int HD_RESOLUTION_WIDTH = 1280;
    public static final int HD_RESOLUTION_HEIGHT = 720;

    //Actually largest width/height
    public static int EXPORTED_IMAGE_WIDTH = 15360;
    public static int EXPORTED_IMAGE_HEIGHT = 8640;

    //mapping getDistance GUI and internal image
    public static double IMAGE_PANEL_WIDTH_RATIO = EXPORTED_IMAGE_WIDTH / (double) PANEL_WIDTH;
    public static double IMAGE_PANEL_HEIGHT_RATIO = EXPORTED_IMAGE_HEIGHT / (double) PANEL_HEIGHT;

//controls

    //TODO initialize from file
    public static final int PAINT_NEXT = KeyEvent.VK_SPACE;
    public static final int PAINT_BIG_STEP = KeyEvent.VK_ENTER;
    public static final int PAINT_FULL_IMAGE = KeyEvent.VK_F;
    public static final int EXPORT_IMAGE = KeyEvent.VK_E;
    public static final int MINIMIZE_WINDOW = KeyEvent.VK_ESCAPE;
    public static final int RESTART_PROGRAM = KeyEvent.VK_R;


//methods used commonly in all projects

    //TODO will delete after ya know...
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     *
     * @param d number to round
     * @return rounded number
     */
    public static int round(double d) {
        return d - (int) d < 0.5 ? (int)d : (int)d + 1;
    }

    /**
     *
     * @param a start point
     * @param b endpoint
     * @return distance between
     */
    public static Double getDistance(FloatPoint a, FloatPoint b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    /**
     *
     * @param a start point
     * @param b endpoint
     * @return midpoint
     */
    public static FloatPoint midPoint(FloatPoint a, FloatPoint b) {
        return new FloatPoint((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
    }

    /**
     * Format error message
     */
    private static final String FORMAT_ERROR = "Wrong format. Try again: ";

    //WONDER WHERE IT IS CALLED
    public static void setDimensionRatios() {
        IMAGE_PANEL_WIDTH_RATIO = EXPORTED_IMAGE_WIDTH / (double) PANEL_WIDTH;
        IMAGE_PANEL_HEIGHT_RATIO = EXPORTED_IMAGE_HEIGHT / (double) PANEL_HEIGHT;

    }

    /**
     *
     * @return integer choice from user
     */
    public static int inputInteger() {
        try {
            return Integer.parseInt(inputString());
        } catch (NumberFormatException e) {
            System.out.println(FORMAT_ERROR);
            return inputInteger();
        }
    }

    /**
     *
     * @return double choice from user
     */
    public static double inputDouble() {
        try {
            return Double.parseDouble(inputString());
        } catch (NumberFormatException e) {
            System.out.println(FORMAT_ERROR);
            return inputDouble();
        }
    }

    /**
     *
     * @return string from user
     */
    public static String inputString(){
        try {
            return br.readLine();
        } catch (Exception e) {
            System.out.println(FORMAT_ERROR);
            return inputString();
        }
    }

    //TODO color chooser GUI

    /**
     *
     * @return color rgb choice from user
     */
    public static int inputColor() {
        //outputs all color choices
        Arrays.stream(COLOR_CHOICES).forEach(x -> System.out.println(x.toString()));
        output("Choose color or input manually: ");
        return inputInteger();
    }

    /**
     *
     * @return user chosen directory path
     */
    public static String getChosenExportDirectory() {
        JFileChooser ch = new JFileChooser();
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ch.showOpenDialog(new Container());

        return ch.getSelectedFile().toString();
    }

    //TODO GUI output
    public static void output(String str) {
        System.out.println(str);
    }


}
