package kiu.oto.AffineTransformation.MainPackage;

import kiu.oto.CommonPackage.AbstractModifier;
import kiu.oto.CommonPackage.FloatPoint;
import kiu.oto.CommonPackage.MyPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.AffineTransformation.MainPackage.SettingsAndMethods.*;
import static kiu.oto.CommonPackage.CommonMethodsAndSettings.*;
import static kiu.oto.AffineTransformation.MainPackage.Modifier.*;
import static kiu.oto.CommonPackage.FloatPoint.*;


//this class contains all attributes necessary to transform the current point

public class Panel extends MyPanel {

    public Panel() {
        setAndDrawCurrent(0.0, 0.0);
    }

    @Override
    protected void setModifier() {
        setPointModifier(new Modifier());
    }

    @Override
    protected Point getUsablePoint(FloatPoint p) {
        //actual bounds of fern are -2.18 <= x <= 2.7 && 0 <= y <= 9.9, and its rotated
        double x = (p.getX() + FERN_DEVIATION_TO_NEGATIVE_X) * PANEL_FERN_WIDTH_RATIO;
        double y = p.getY() * PANEL_FERN_HEIGHT_RATIO;

        int paintableX = round(y);
        int paintableY = round(x);

        return new Point(paintableX, paintableY);
    }


    @Override
    protected void doRelevantAction(MouseEvent e) {

    }


}

