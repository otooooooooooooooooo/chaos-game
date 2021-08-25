package kiu.oto.common;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static kiu.oto.common.CommonMethodsAndSettings.DOT_COLOR_1;

//point class which saves floating x and y coordinates to maintain precision


@RequiredArgsConstructor
public class FloatPoint {
    @Getter
    @Setter
    @NonNull
    private Double x;
    @Getter
    @Setter
    @NonNull
    private Double y;
    @Getter
    @Setter
    protected int color = DOT_COLOR_1;

    @Getter
    private static AbstractModifier modifier;

    public FloatPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void modify() {
        modifier.modify(this);
    }

    public static void setPointModifier(AbstractModifier pointModifier) {
        modifier = pointModifier;
    }
}
