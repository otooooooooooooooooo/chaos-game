package kiu.oto.custom;

import java.awt.event.MouseEvent;

public enum CustomSettingsAndMethods {;

//mouse controls
    public static final int CREATE_VERTEX = MouseEvent.BUTTON3;
    public static final int PAINT_CURRENT = MouseEvent.BUTTON1;


    public static boolean parseBoolean(String s) throws IllegalArgumentException{
        if(s.equalsIgnoreCase("true"))
            return true;
        else if (s.equalsIgnoreCase("false"))
            return false;
        else throw new IllegalArgumentException();
    }

}
