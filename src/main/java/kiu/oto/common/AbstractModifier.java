package kiu.oto.common;

import java.util.Random;

/**
 * Modifier class has method modify(FloatPoint p) which determines how the current point
 * of panel should be iterated. it saves variables such as vertices or other
 * necessary parameters and methods.
 */

public abstract class AbstractModifier {
    /**
     * Random object which makes
     * randomization of
     * point modifying
     * possible
     */
    protected static final Random RANDOM = CommonMethodsAndSettings.RANDOM;

    /**
     * Default constructor
     */
    public AbstractModifier() {
        prepareSetup();
    }

    /**
     * Method to load/initialize all
     * necessary objects for modification
     */
    public abstract void prepareSetup();

    /**
     *
     * @param point to be modified
     *
     */
    public abstract void modify(FloatPoint point);
}
