package kiu.oto.ferns;

import kiu.oto.common.FloatPoint;
import kiu.oto.common.CommonPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.ferns.FernsSettingsAndMethods.*;
import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.common.FloatPoint.*;


//this class contains all attributes necessary to transform the current point

public class FernsPanel extends CommonPanel {

    public FernsPanel() {
        setAndDrawCurrent(0.0, 0.0);
    }

    @Override
    protected void setModifier() {
        setPointModifier(new FernsModifier());
    }

    @Override
    protected void getInputParameters() {
        try {
            System.out.println("Input value of b1 (default value = " + B1_DEFAULT_VALUE + "):");
            FernsModifier.setB1(inputDouble());
        } catch (Exception e) {
            System.out.println("Using default value");
        }

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

