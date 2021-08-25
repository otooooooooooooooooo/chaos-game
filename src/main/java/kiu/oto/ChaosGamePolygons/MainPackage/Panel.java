package kiu.oto.ChaosGamePolygons.MainPackage;

import kiu.oto.CommonPackage.FloatPoint;
import kiu.oto.CommonPackage.MyPanel;


import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.CommonPackage.CommonMethodsAndSettings.*;

public class Panel extends MyPanel {
    public Panel() {
        setAndDrawCurrent(EXPORTED_IMAGE_WIDTH / 2.0, EXPORTED_IMAGE_HEIGHT / 2.0);
    }


    @Override
    protected void setModifier() {
        Modifier modifier = new Modifier();
        FloatPoint.setPointModifier(modifier);
        modifier.getCorners().forEach(this::paint);

    }

    @Override
    protected Point getUsablePoint(FloatPoint p) {
        return new Point(round(p.getX()), round(p.getY()));
    }

    @Override
    protected void doRelevantAction(MouseEvent e) {

    }

}
