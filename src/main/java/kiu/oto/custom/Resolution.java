package kiu.oto.custom;

import kiu.oto.common.inputs.enums.ResolutionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static kiu.oto.common.CommonMethodsAndSettings.*;
import static kiu.oto.common.inputs.enums.ResolutionType.*;


@AllArgsConstructor
public class Resolution {
    public static final Resolution[] RESOLUTIONS = new Resolution[] {
            new Resolution(HD, 1),
            new Resolution(FULL_HD, 1.5),
            new Resolution($4K, 3),
            new Resolution($8K, 6),
            new Resolution($16K, 12)

    };

    public static double getByType(ResolutionType resolutionType) {
        for (Resolution resolution: RESOLUTIONS)
            if(resolution.getResolutionType() == resolutionType)
                return resolution.HD_ratio;
        return 1;
    }


    @Getter
    private final ResolutionType resolutionType;
    @Getter
    private final double HD_ratio;


    @Override
    public String toString() {
        String name = switch (resolutionType) {
            case HD -> "HD";
            case FULL_HD -> "Full HD";
            case $4K -> "4K";
            case $8K -> "8K";
            case $16K -> "16K";
         };
        return name + " - " +  (int)(HD_RESOLUTION_WIDTH * HD_ratio) + "x" + (int)(HD_RESOLUTION_HEIGHT * HD_ratio);
    }

}
