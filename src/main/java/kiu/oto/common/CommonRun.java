package kiu.oto.common;


import kiu.oto.common.inputs.MultiChoiceInputPanel;
import kiu.oto.common.inputs.PopupDialogFrame;
import kiu.oto.common.inputs.MultiChoiceHandler;
import kiu.oto.common.inputs.enums.ProgramChoice;

import kiu.oto.common.inputs.enums.ResolutionType;
import kiu.oto.custom.CustomPanel;

import kiu.oto.ferns.FernsPanel;

import kiu.oto.polygons.PolygonsPanel;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.custom.Resolution.getByType;

public class CommonRun {

    public static CommonFrame frame;

    public static void startProgram() {
        ProgramChoice program = getProgramChoice();

        output("Choose background: ");
        BACKGROUND_COLOR = inputColor();

        setExportedImageResolution();

        frame = new CommonFrame();

        switch (program) {
            case CUSTOM -> new CustomPanel();
            case POLYGONS -> new PolygonsPanel();
            case FERNS -> new FernsPanel();
        }
    }

    private static ProgramChoice getProgramChoice() {
        return new PopupDialogFrame<>(
                new MultiChoiceInputPanel<>(
                        "Choose simulation", MultiChoiceHandler.programChoiceHandler)).getInput();
    }

    private static void setExportedImageResolution() {
        ResolutionType resolutionType = new PopupDialogFrame<>(new MultiChoiceInputPanel<>(
                "Choose exported image resolution",MultiChoiceHandler.resolutionTypeHandler)).getInput();

        double CHOICE_HD_RATIO = getByType(resolutionType);


        EXPORTED_IMAGE_WIDTH = (int) (HD_RESOLUTION_WIDTH * CHOICE_HD_RATIO);
        EXPORTED_IMAGE_HEIGHT = (int) (HD_RESOLUTION_HEIGHT * CHOICE_HD_RATIO);
        setDimensionRatios(CHOICE_HD_RATIO);
    }

}
