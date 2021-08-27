package kiu.oto.common.inputs;

import kiu.oto.common.inputs.enums.ProgramChoice;
import kiu.oto.common.inputs.enums.ResolutionType;
import kiu.oto.common.inputs.enums.RuleName;
import kiu.oto.common.inputs.enums.TemplateMode;
import kiu.oto.custom.Mode;
import kiu.oto.custom.Resolution;
import kiu.oto.polygons.Rule;

import javax.swing.*;
import java.util.Arrays;

import static kiu.oto.custom.Mode.MODES;
import static kiu.oto.polygons.Rule.RULES;

import static kiu.oto.custom.Resolution.RESOLUTIONS;

public abstract class MultiChoiceHandler<Type> {

    public abstract String[] getTexts();

    public abstract String[] getActionCommands();

    public abstract Type getType(ButtonModel selectedModel);

    public static final MultiChoiceHandler<ProgramChoice> programChoiceHandler = new MultiChoiceHandler<>() {
        @Override
        public String[] getTexts() {
            return getActionCommands();
        }

        @Override
        public String[] getActionCommands() {
            return Arrays.stream(ProgramChoice.values()).map(ProgramChoice::toString).toArray(String[]::new);
        }

        @Override
        public ProgramChoice getType(ButtonModel selectedModel) {
            return ProgramChoice.valueOf(selectedModel.getActionCommand());
        }
    };

    public static final MultiChoiceHandler<RuleName> ruleSetChoiceHandler = new MultiChoiceHandler<>() {
        @Override
        public String[] getTexts() {
            return Arrays.stream(RULES).map(Rule::getRuleDefinition).toArray(String[]::new);
        }

        @Override
        public String[] getActionCommands() {
            return Arrays.stream(RuleName.values()).map(RuleName::toString).toArray(String[]::new);
        }

        @Override
        public RuleName getType(ButtonModel selectedModel) {
            return RuleName.valueOf(selectedModel.getActionCommand());
        }
    };

    public static final MultiChoiceHandler<TemplateMode> templateModeHandler = new MultiChoiceHandler<>() {
        @Override
        public String[] getTexts() {
            return Arrays.stream(MODES).map(Mode::getDescription).toArray(String[]::new);
        }

        @Override
        public String[] getActionCommands() {
            return Arrays.stream(TemplateMode.values()).map(TemplateMode::toString).toArray(String[]::new);
        }

        @Override
        public TemplateMode getType(ButtonModel selectedModel) {
            return TemplateMode.valueOf(selectedModel.getActionCommand());
        }
    };

    public static final MultiChoiceHandler<ResolutionType> resolutionTypeHandler = new MultiChoiceHandler<>() {
        @Override
        public String[] getTexts() {
            return Arrays.stream(RESOLUTIONS).map(Resolution::toString).toArray(String[]::new);
        }

        @Override
        public String[] getActionCommands() {
            return Arrays.stream(ResolutionType.values()).map(ResolutionType::toString).toArray(String[]::new);
        }

        @Override
        public ResolutionType getType(ButtonModel selectedMode) {
            return ResolutionType.valueOf(selectedMode.getActionCommand());
        }
    };


}
