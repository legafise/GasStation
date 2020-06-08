package com.lashkevich.station.entity;

import com.lashkevich.station.constant.Constant;

import java.util.Arrays;

public enum Color {
    VIOLET("Violet"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    PINK("Pink"),
    WHITE("White"),
    ORANGE("Orange"),
    RED("Red"),
    GREEN("Green");

    private String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    public static Color getRandomColor() {
        long colorNumber = Math.round(Math.random() * (Color.values().length - 1));
        return Arrays.stream(Color.values()).
                filter((color) -> color.ordinal() == colorNumber).findFirst().orElse(Constant.DEFAULT_COLOR);
    }
}
