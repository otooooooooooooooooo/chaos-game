package kiu.oto.custom;

import kiu.oto.common.inputs.enums.TemplateMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static kiu.oto.common.inputs.enums.TemplateMode.*;

@AllArgsConstructor
public class Mode {
    public static final Mode[] MODES = new Mode[] {
      new Mode(IMPORT, "Import existing template"),
      new Mode(MANUAL, "Define vertices manually")
    };

    @Getter
    private final TemplateMode templateMode;
    @Getter
    private final String description;
}
