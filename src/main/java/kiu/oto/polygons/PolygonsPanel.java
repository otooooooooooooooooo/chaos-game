package kiu.oto.polygons;

import kiu.oto.common.FloatPoint;
import kiu.oto.common.CommonPanel;


import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.polygons.PolygonsSettingsAndMethods.DEFAULT_POLYGON_CORNER_COUNT;
import static kiu.oto.polygons.PolygonsSettingsAndMethods.rules;

public class PolygonsPanel extends CommonPanel {
    public PolygonsPanel() {
        setAndDrawCurrent(EXPORTED_IMAGE_WIDTH / 2.0, EXPORTED_IMAGE_HEIGHT / 2.0);
    }


    @Override
    protected void setModifier() {
        PolygonsModifier polygonsModifier = new PolygonsModifier();
        FloatPoint.setPointModifier(polygonsModifier);
        polygonsModifier.getCorners().forEach(this::paint);

    }

    @Override
    protected void getInputParameters() {
        try {
            System.out.println("Input polygon corner count (4, 5, 6, 7 recommended):");
            int input = inputInteger();
            if(input < 1)
                throw new Exception();
            PolygonsModifier.setPolygonCornerCount(input);
        } catch (Exception e) {
            System.out.println("Using default corner count: " + DEFAULT_POLYGON_CORNER_COUNT + '\n');
        }

        try {
            for (Rule x : rules) {
                System.out.println(x.toString());
            }
            System.out.println("Input index of rule set:");
            int input = inputInteger();
            if(input < 0 || input >= rules.size())
                throw new Exception();
            PolygonsModifier.setRuleSet(input);
        } catch (Exception e) {
            System.out.println("Using default rule set: " + PolygonsModifier.getRuleSet());
        }


    }

    @Override
    protected Point getUsablePoint(FloatPoint p) {
        return new Point(round(p.getX()), round(p.getY()));
    }

    @Override
    protected void doRelevantAction(MouseEvent e) {

    }

}
