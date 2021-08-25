package kiu.oto.ferns;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public enum SettingsAndMethods {;

    public static final double B1_DEFAULT_VALUE = 0.04;
    //parameters of default fern found on wikipedia
    public static final double FERN_DEFAULT_HEIGHT = 10.0;
    public static final double FERN_DEFAULT_WIDTH = 5.632;
    public static final double FERN_DEVIATION_TO_NEGATIVE_X = 2.5;
    //precalculated values of ratios considering rotation
    public static final double PANEL_FERN_WIDTH_RATIO = EXPORTED_IMAGE_WIDTH / FERN_DEFAULT_HEIGHT;
    public static final double PANEL_FERN_HEIGHT_RATIO = EXPORTED_IMAGE_HEIGHT / FERN_DEFAULT_WIDTH;

}
