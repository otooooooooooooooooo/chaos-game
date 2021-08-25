package kiu.oto.common;

import java.util.ArrayList;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public class Vertex extends FloatPoint {
    //will need these numbers often
    private static final double sin45 = Math.sqrt(2.0) / 2;

    private final double degree;
    private final boolean clockwise;
    private final double compressionRatio;
    private final int quantity;
    private double sine;
    private double cosine;

    public Vertex(double x, double y, double compressionRatio, double degree, boolean clockwise, int color, int quantity) {
        super(x, y);

        if (degree < 0)
            throw new IllegalArgumentException("Degree value >=0 expected");

        /*
        refactoring parameters so that degree is <= 90
        without changing final result
         */

        //subtracting period of rotation
        while (degree >= 360)
            degree -= 360;

        //rotation with 180 degree is same as symmetry
        if (degree >= 180) {
            compressionRatio *= -1;
            degree -= 180;
        }

        if (degree > 90) {
            compressionRatio *= -1;
            clockwise = !clockwise;
            degree = 180 - degree;
        }

        this.compressionRatio = compressionRatio;
        this.degree = degree;
        this.clockwise = clockwise;
        this.color = color;
        this.quantity = quantity;

        setSineCosine();
    }

    public int getQuantity() {
        return quantity;
    }

    private void setSineCosine() {
        sine = Math.sin(Math.toRadians(degree / 2.0));
        cosine = Math.cos(Math.toRadians(degree / 2.0));
    }

    //returns the result of interaction of point p with this vertex
    public FloatPoint next(FloatPoint p) {
        return getCompressed(getRotated(p));
    }

    private FloatPoint getCompressed(FloatPoint p) {
        double x = (p.getX() + (compressionRatio - 1) * getX()) / compressionRatio;
        double y = (p.getY() + (compressionRatio - 1) * getY()) / compressionRatio;
        return new FloatPoint(x, y);
    }

    //returns the result of p's rotation in respect to this vertex by 'degree' degrees
    private FloatPoint getRotated(FloatPoint p) {
        /*
        constructor should make sure that:
        */
        assert (degree >= 0 && degree <= 90);

        if (degree == 0)
            return p;
        int quarter = getQuarterOf(p);
        Circle circle = new Circle(this, between(this, p));


        for (double precision = 0.0; precision <= 1.0; precision = precision + 0.01) {
            for (FloatPoint a : circle) {
                if (!isEligible(p, a, quarter))
                    continue;
                double side1 = between(a, p) / 2;
                double side2 = between(circle.center, midPoint(a, p));
                if (Math.abs(side1 / circle.radius - sine) <= precision && Math.abs(side2 / circle.radius - cosine) <= precision) {
                    return a;
                }
            }
        }
        return p;
    }

    /*returns in which quarter the point P is in respect to this vertex.
    the quarters are separated by how the x and y coordinates
    are changing when the point from this quarter rotates by x < 90 degrees
     */
    private int getQuarterOf(FloatPoint p) {
        double constXY = between(this, p) * sin45;
        if (p.getX() >= this.getX() + constXY)
            return 1;
        if (p.getX() <= this.getX() - constXY)
            return 3;
        if (p.getY() < this.getY() - constXY)
            return 2;
        if (p.getY() > this.getY() + constXY)
            return 4;

        return 0;
    }


    //checks whether the option point is in correct quarter as it should be
    private boolean isEligible(FloatPoint initial, FloatPoint option, int quarter) {
        switch (quarter) {
            case (1) -> {
                if (clockwise)
                    return option.getY() > initial.getY();
                else return option.getY() < initial.getY();
            }
            case (2) -> {
                if (clockwise)
                    return option.getX() > initial.getX();
                else return option.getX() < initial.getX();
            }
            case (3) -> {
                if (clockwise)
                    return option.getY() < initial.getY();
                else return option.getY() > initial.getY();
            }
            case (4) -> {
                if (clockwise)
                    return option.getX() < initial.getX();
                else return option.getX() > initial.getX();
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return getX() + "|" + getY() + "|" + compressionRatio + "|" + degree + "|" + clockwise + "|" + color + "|" + quantity + '\n';
    }

}

class Circle extends ArrayList<FloatPoint> {

    public final FloatPoint center;
    public final double radius;

    public Circle(FloatPoint center, double radius) {
        this.radius = radius;
        this.center = center;
        for (double x = -radius; x <= radius; x++) {
            double y = Math.sqrt((radius - x) * (radius + x));
            add(new FloatPoint(center.getX() + x, center.getY() + y));
            add(new FloatPoint(center.getX() + x, center.getY() - y));
        }

    }
}
