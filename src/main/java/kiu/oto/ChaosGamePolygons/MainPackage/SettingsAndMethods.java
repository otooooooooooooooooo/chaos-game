package kiu.oto.ChaosGamePolygons.MainPackage;

import java.util.ArrayList;
import static kiu.oto.CommonPackage.CommonMethodsAndSettings.*;

public enum SettingsAndMethods {
    ;
    public static final int DEFAULT_POLYGON_CORNER_COUNT = 4;

    //rules
    public static final ArrayList<Rule> rules = new ArrayList<>();

    public static final Rule NO_RULE = new Rule(0, "No rules");
    public static final Rule NO_REPETITION = new Rule(1, "Do not repeat corner twice in a row");
    public static final Rule NO_REPETITION_X3 = new Rule(2, "Do not repeat corner 3 times in a row");
    public static final Rule NO_OPPOSITE = new Rule(3, "(for square) Do not choose opposite of the last corner");
    public static final Rule NO_CLOCKWISE_NEIGHBOR = new Rule(4, "(for square) Do not choose clockwise neighbour of previous");
    public static final Rule NO_REPETITION_TWO_BEFORE = new Rule(5, "Do not repeat corner from two moves before");
    public static final Rule NO_OPPOSITE_TWO_BEFORE = new Rule(6, "(For square) Do not chose opposite of corner two moves before");
    public static final Rule NO_NEIGHBOUR_IF_LAST_TWO_SAME = new Rule(7, "Do not choose neighbour of previous, if last two corners were same");
    public static final Rule NO_NEIGHBOUR_IF_LAST_THREE_SAME = new Rule(8, "Do not choose neighbour of previous, if last three corners were same");


    //geometric configurations
    public static final double GAP_FROM_BORDER_TO_CORNER = EXPORTED_IMAGE_HEIGHT / 100.0;
}
class Rule {
    public int getRuleNumber() {
        return RULE_NUMBER;
    }

//    public String getRuleDefinition() {
//        return RULE_DEFINITION;
//    }

    private final int RULE_NUMBER;
    private final String RULE_DEFINITION;

    public Rule(int rule_number, String rule_definition) {
        RULE_NUMBER = rule_number;
        RULE_DEFINITION = rule_definition;
        SettingsAndMethods.rules.add(this);
    }

    public String toString() {
        return RULE_NUMBER + ". " + RULE_DEFINITION + '\n';
    }
}
