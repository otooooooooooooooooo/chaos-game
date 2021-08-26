package kiu.oto.common;


import kiu.oto.common.inputs.ProgramChoice;

import kiu.oto.custom.CustomPanel;

import kiu.oto.ferns.FernsPanel;

import kiu.oto.polygons.PolygonsPanel;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.common.inputs.ProgramChoice.*;

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
    //    ProgramChoiceInput pri = new ProgramChoiceInput();
        return POLYGONS;
    }

    private static void setExportedImageResolution() {
        output("Choose exported image resolution: " +
                "1 - HD, 2 - FULL HD, 3 - 4K, 4 - 8K, 5 - 16K " +
                "(this might effect program execution speed):");
        int resolutionChoice;

        do {
            resolutionChoice = inputInteger();
        } while (resolutionChoice < 1 || resolutionChoice > 5);

        double CHOICE_HD_RATIO;

        if(resolutionChoice == 1)
            CHOICE_HD_RATIO = 1;
        else if (resolutionChoice == 2)
            CHOICE_HD_RATIO = 1.5;
        else if (resolutionChoice == 3)
            CHOICE_HD_RATIO = 3;
        else if (resolutionChoice == 4)
            CHOICE_HD_RATIO = 6;
        else
            CHOICE_HD_RATIO = 12;

        EXPORTED_IMAGE_WIDTH = (int) (HD_RESOLUTION_WIDTH * CHOICE_HD_RATIO);
        EXPORTED_IMAGE_HEIGHT = (int) (HD_RESOLUTION_HEIGHT * CHOICE_HD_RATIO);
        setDimensionRatios();
        output("Resolution set to " + EXPORTED_IMAGE_WIDTH + "x" + EXPORTED_IMAGE_HEIGHT);
    }

}
