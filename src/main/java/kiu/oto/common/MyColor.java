package kiu.oto.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyColor {
    private final String name;
    private final int RGB;

    public String toString() {
        return name + " - " + RGB;
    }
}
