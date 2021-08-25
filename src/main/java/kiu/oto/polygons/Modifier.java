package kiu.oto.polygons;

import kiu.oto.common.AbstractModifier;
import kiu.oto.common.FloatPoint;
import kiu.oto.common.Vertex;

import java.util.ArrayList;

import static kiu.oto.polygons.SettingsAndMethods.*;
import static kiu.oto.common.CommonMethodsAndSettings.*;

public class Modifier extends AbstractModifier {
    private static int polygonCornerCount = DEFAULT_POLYGON_CORNER_COUNT;

    //previously chosen corners
    Vertex previous = null;
    Vertex previousX2 = null;
    Vertex previousX3 = null;

    //defines which rule set the modifier follows
    private static int ruleSet = NO_RULE.getRuleNumber();
    private static final ArrayList<Vertex> corners = new ArrayList<>();

    public static int getRuleSet() {
        return ruleSet;
    }

    public static void setRuleSet(int ruleNumber) {
        ruleSet = ruleNumber;
    }

    public static void setPolygonCornerCount(int polygonCornerCount) {
        Modifier.polygonCornerCount = polygonCornerCount;
    }

    private Vertex chooseCorner() {
        Vertex next = getRandomCorner();
        if(satisfiesRules(next))
            return next;
        else return chooseCorner();

    }

    private Vertex getRandomCorner() {
        return corners.get(RANDOM.nextInt(polygonCornerCount));
    }

    private boolean satisfiesRules(Vertex corner) { //TODO switch
        if(ruleSet == NO_RULE.getRuleNumber())
            return true;
        if(ruleSet == NO_REPETITION.getRuleNumber())
            return !corner.equals(previous);
        if(ruleSet == NO_REPETITION_X3.getRuleNumber())
            return !(corner.equals(previous) && previous.equals(previousX2));
        if(ruleSet == NO_OPPOSITE.getRuleNumber())
            return previous == null || notOpposite(corner, previous);
        if(ruleSet == NO_CLOCKWISE_NEIGHBOR.getRuleNumber())
            return previous == null || !isClockwiseNeighbour(corner, previous);
        if(ruleSet == NO_REPETITION_TWO_BEFORE.getRuleNumber())
            return !corner.equals(previousX2);
        if(ruleSet == NO_OPPOSITE_TWO_BEFORE.getRuleNumber())
            return previousX2 == null || notOpposite(corner, previousX2);
        if(ruleSet == NO_NEIGHBOUR_IF_LAST_TWO_SAME.getRuleNumber())
            return previousX2 == null || previous == null || !(previous.equals(previousX2) && isNeighbour(corner, previous));
        if(ruleSet == NO_NEIGHBOUR_IF_LAST_THREE_SAME.getRuleNumber())
            return previousX3 == null || previousX2 == null || previous == null || !(previous.equals(previousX2) && previous.equals(previousX3) && isNeighbour(corner, previous));
        else return true;
    }

    private boolean notOpposite(Vertex a, Vertex b) {
        int na = corners.indexOf(a);
        int nb = corners.indexOf(b);

        return (na + nb) % 2 == 0 && na != nb;
    }

    private boolean isClockwiseNeighbour(Vertex candidate, Vertex previous) {
        int nCandidate = corners.indexOf(candidate);
        int nPrevious = corners.indexOf(previous);

        return nCandidate - nPrevious == 1 || nCandidate == 0 && nPrevious == corners.size() - 1;
    }

    private boolean isNeighbour(Vertex candidate, Vertex previous) {
        return isClockwiseNeighbour(candidate, previous) || isClockwiseNeighbour(previous, candidate);
    }

    public ArrayList<Vertex> getCorners() {
        return corners;
    }

    private void loadCorners() {
        Vertex center = new Vertex(EXPORTED_IMAGE_WIDTH / 2.0, EXPORTED_IMAGE_HEIGHT / 2.0, 1.0, 360 / (double) polygonCornerCount, true, DOT_COLOR_1, 1);

        FloatPoint nextPoint = new FloatPoint(EXPORTED_IMAGE_WIDTH / 2.0, GAP_FROM_BORDER_TO_CORNER);
        for(int i = 0; i < polygonCornerCount; i++) {
            nextPoint = center.next(nextPoint);
            Vertex next = new Vertex(nextPoint.getX(), nextPoint.getY(), 2.0, 0.0, true, DOT_COLOR_1, 1);
            next.setColor(RANDOM.nextInt());
            corners.add(next);
        }

    }

    @Override
    public void prepareSetup() {
        loadCorners();
    }

    @Override
    public void modify(FloatPoint point) {
        Vertex current = chooseCorner();
        FloatPoint modified = current.next(point);
        point.setX(modified.getX());
        point.setY(modified.getY());
        point.setColor(current.getColor());

        previousX3 = previousX2;
        previousX2 = previous;
        previous = current;
    }

}
