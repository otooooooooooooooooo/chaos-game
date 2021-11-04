package kiu.oto.common.inputs.enums;

import lombok.Getter;

public enum RotationDirection {
    CLOCKWISE(true, "Clockwise"),
    COUNTER_CLOCKWISE(false, "Counter clockwise");

    @Getter
    private final boolean clockwise;

    @Getter
    private final String name;

    RotationDirection(boolean clockwise, String name) {
        this.clockwise = clockwise;
        this.name = name;
    }
}
