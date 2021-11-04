package kiu.oto.polygons;

import kiu.oto.common.inputs.enums.RuleName;
import lombok.Getter;

public class Rule {

    public static final Rule[] RULES = new Rule[] {new Rule(RuleName.NO_RULE, "No rules"),
            new Rule(RuleName.NO_REPETITION, "Do not repeat corner twice in a row"),
            new Rule(RuleName.NO_REPETITION_X3, "Do not repeat corner 3 times in a row"),
            new Rule(RuleName.NO_OPPOSITE, "(for square) Do not choose opposite of the last corner"),
            new Rule(RuleName.NO_CLOCKWISE_NEIGHBOR, "(for square) Do not choose clockwise neighbour of previous"),
            new Rule(RuleName.NO_REPETITION_TWO_BEFORE, "Do not repeat corner from two moves before"),
            new Rule(RuleName.NO_OPPOSITE_TWO_BEFORE, "(For square) Do not chose opposite of corner two moves before"),
            new Rule(RuleName.NO_NEIGHBOUR_IF_LAST_TWO_SAME,
                    "Do not choose neighbour of previous, if last two corners were same"),
            new Rule(RuleName.NO_NEIGHBOUR_IF_LAST_THREE_SAME,
                    "Do not choose neighbour of previous, if last three corners were same")
    };

    @Getter
    public final RuleName ruleSet;
    @Getter
    private final String ruleDefinition;

    public Rule(RuleName ruleSet, String rule_definition) {
        this.ruleSet = ruleSet;
        this.ruleDefinition = rule_definition;
    }

}
