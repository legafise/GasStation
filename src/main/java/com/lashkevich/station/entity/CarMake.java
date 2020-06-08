package com.lashkevich.station.entity;

import com.lashkevich.station.constant.Constant;

import java.util.Arrays;

public enum CarMake {
    BMW("BMW"),
    BENTLEY("Bentley"),
    FERRARI("Ferrari"),
    JAGUAR("Jaguar"),
    MAZDA("Mazda"),
    NISAN("Nisan"),
    TOYOTA("Toyota"),
    SKODA("Skoda");

    private String makeName;

    CarMake(String info) {
        this.makeName = info;
    }

    public String getMakeName() {
        return makeName;
    }

    public static CarMake getRandomMark() {
        long markNumber = Math.round(Math.random() * (CarMake.values().length - 1));
        return Arrays.stream(CarMake.values()).
                filter((carMake) -> carMake.ordinal() == markNumber).findFirst().orElse(Constant.DEFAULT_CAR_MAKE);
    }
}
