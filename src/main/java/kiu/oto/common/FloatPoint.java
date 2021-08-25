package kiu.oto.common;

import static kiu.oto.common.CommonMethodsAndSettings.DOT_COLOR_1;

//point class which saves floating x and y coordinates to maintain precision

public class FloatPoint {
    private double x;
    private double y;
    protected int color = DOT_COLOR_1;
    private static AbstractModifier modifier;

    public FloatPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void modify() {
        modifier.modify(this);
    }

    public static void setPointModifier(AbstractModifier pointModifier) {
        modifier = pointModifier;
    }

    public static AbstractModifier getModifier() { return modifier; }
}