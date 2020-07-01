package com.lashkevich.station.util.generator;

public class IdGenerator {
    private static long carId = 0;
    private static long fillingStationId = 0;

    public static long generateCarId() {
        return carId++;
    }

    public static long generateFillingStationId() {
        return fillingStationId++;
    }
}
