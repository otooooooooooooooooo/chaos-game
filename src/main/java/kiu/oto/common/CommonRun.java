package kiu.oto.common;

import kiu.oto.custom.CustomRun;
import kiu.oto.ferns.FernsRun;
import kiu.oto.polygons.PolygonsRun;

import static kiu.oto.common.CommonMethodsAndSettings.*;

public class CommonRun {

    public static void startProgram() {
        int program = getProgramChoice();

        System.out.println("Choose background: ");
        BACKGROUND_COLOR = inputColor();

        setExportedImageResolution();

        if(program == 1)
            FernsRun.run();
        if(program == 2)
            PolygonsRun.run();
        if(program == 3)
            CustomRun.run();
    }

    private static int getProgramChoice() {
        System.out.println("Choose simulation program:\n" +
                "1: Affine Transformation\n" +
                "2: Chaos Game Polygons\n" +
                "3: Chaos Game Advanced");
        int program;
        do {
            program = inputInteger();
        } while (program != 1 && program != 2 && program != 3);
        return program;
    }

    private static void setExportedImageResolution() {
        System.out.println("Choose exported image resolution: " +
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
        System.out.println("Resolution set to " + EXPORTED_IMAGE_WIDTH + "x" + EXPORTED_IMAGE_HEIGHT);
    }

}
