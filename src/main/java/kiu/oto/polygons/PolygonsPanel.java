package kiu.oto.polygons;

import kiu.oto.common.FloatPoint;
import kiu.oto.common.CommonPanel;


import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.common.CommonMethodsAndSettings.*;

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
    protected Point getUsablePoint(FloatPoint p) {
        return new Point(round(p.getX()), round(p.getY()));
    }

    @Override
    protected void doRelevantAction(MouseEvent e) {

    }

}
