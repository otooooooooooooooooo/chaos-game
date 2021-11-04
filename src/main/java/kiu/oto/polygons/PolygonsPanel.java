package kiu.oto.polygons;

import kiu.oto.common.FloatPoint;
import kiu.oto.common.CommonPanel;
import kiu.oto.common.inputs.IntegerInputPanel;
import kiu.oto.common.inputs.MultiChoiceInputPanel;
import kiu.oto.common.inputs.PopupDialogFrame;
import kiu.oto.common.inputs.MultiChoiceHandler;

import java.awt.*;
import java.awt.event.MouseEvent;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.polygons.PolygonsSettingsAndMethods.DEFAULT_POLYGON_CORNER_COUNT;

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
        setCornerCount();

        setRules();

    }

    private void setCornerCount() {
        PolygonsModifier.setPolygonCornerCount(
                new PopupDialogFrame<>(new IntegerInputPanel(
                        "Choose corner count", 3, 1, 500)).getInput());
           }

    private void setRules() {
        PolygonsModifier.setRuleName(new PopupDialogFrame<>(
                new MultiChoiceInputPanel<>("Choose rules", MultiChoiceHandler.ruleSetChoiceHandler())
        ).getInput());


    }

    @Override
    protected Point getUsablePoint(FloatPoint p) {
        return new Point(round(p.getX()), round(p.getY()));
    }

    @Override
    protected void doRelevantAction(MouseEvent e) {

    }

}
