package com.lashkevich.station.util.randomizer;

import com.lashkevich.station.entity.Car;
import com.lashkevich.station.entity.CarMake;
import com.lashkevich.station.entity.Color;

public class CarRandomizer {
    public static Car generateRandomCar() {
        return new Car(CarMake.getRandomMark(), Color.getRandomColor());
    }
}
