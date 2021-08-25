package kiu.oto.custom;

import kiu.oto.common.CommonFrame;

public class CustomRun {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        new CommonFrame(new CustomPanel());
    }
}
