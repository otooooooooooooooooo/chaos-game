package kiu.oto.common;

import java.util.Random;

/**
 * modifier class has method modify(FloatPoint p) which determines how the current point
 * of panel should be iterated. it saves variables such as vertices or other
 * necessary parameters and methods.
 */

public abstract class AbstractModifier {
    //TODO initialize random object in commonMethodsAndSettings
    protected static final Random RANDOM = CommonMethodsAndSettings.RANDOM;
    
    public AbstractModifier() {
        prepareSetup();
    }

    public abstract void prepareSetup();

    public abstract void modify(FloatPoint point);
}
