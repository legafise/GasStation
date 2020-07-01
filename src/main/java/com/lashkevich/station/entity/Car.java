package com.lashkevich.station.entity;

import com.lashkevich.station.constant.Constant;
import com.lashkevich.station.util.generator.IdGenerator;
import com.lashkevich.station.util.randomizer.BigDecimalRandomizer;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class Car extends Thread {
    private static final Logger LOGGER = Logger.getLogger(Car.class.getName());
    private long id;
    private CarMake carMake;
    private Color color;
    private BigDecimal fuel;

    public Car(CarMake carMake, Color color) {
        this.id = IdGenerator.generateCarId();
        this.carMake = carMake;
        this.color = color;
        this.fuel = BigDecimalRandomizer.generateRandomBigDecimal(Constant.MIN_CAR_FUEL, Constant.MAX_CAR_FUEL);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(CarMake carMake) {
        this.carMake = carMake;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BigDecimal getFuel() {
        return fuel;
    }

    public void setFuel(BigDecimal fuel) {
        this.fuel = fuel;
    }

    @Override
    public void run() {
        int fillingStationId = (int) (Math.round(Math.random() * (GasStation.getInstance().getFillingStations().size() - 1)));

        if (Math.round(Math.random() * 1) == 1) {
            GasStation.getInstance().getFillingStations().get(fillingStationId).
                    fillTank(this, BigDecimalRandomizer.generateRandomBigDecimal(Constant.MIN_CAR_FUEL, Constant.MAX_CAR_FUEL.subtract(fuel)));
        } else {
            GasStation.getInstance().getFillingStations().get(fillingStationId).fillFullTank(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carMake=" + carMake.getMakeName() +
                ", color=" + color.getColorName() +
                ", fuel=" + fuel + "%" +
                '}';
    }
}
