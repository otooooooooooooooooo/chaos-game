package kiu.oto.common;

public class MyColor {
    private final int RGB;
    private final String name;

    public MyColor(String name, int RGB) {
        this.name = name;
        this.RGB = RGB;
    }

    public String toString() {
        return name + " - " + RGB;
    }
}
