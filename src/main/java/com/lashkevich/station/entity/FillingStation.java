package com.lashkevich.station.entity;

import com.lashkevich.station.constant.Constant;
import com.lashkevich.station.exception.FillingException;
import com.lashkevich.station.util.generator.IdGenerator;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FillingStation {
    private static final Logger LOGGER = Logger.getLogger(FillingException.class.getName());
    private Lock LOCK = new ReentrantLock();
    private boolean isLocked;
    private long id;
    private BigDecimal fuel;

    public FillingStation(BigDecimal fuel) throws FillingException {
        if (fuel.compareTo(Constant.MAX_STATION_FUEL) > 0 || fuel.compareTo(Constant.MIN_STATION_FUEL) < 0) {
            throw new FillingException();
        }

        this.id = IdGenerator.generateFillingStationId();
        this.fuel = fuel;
    }

    public FillingStation() {
        this.id = IdGenerator.generateFillingStationId();
        this.fuel = Constant.MAX_STATION_FUEL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getFuel() {
        return fuel;
    }

    public void setFuel(BigDecimal fuel) throws FillingException {
        if (fuel.compareTo(Constant.MAX_STATION_FUEL) > 0 || fuel.compareTo(Constant.MIN_STATION_FUEL) < 0) {
            throw new FillingException("Введено некорректное значение!");
        }

        this.fuel = fuel;
    }

    public void fillTank(Car car, BigDecimal gasAmount) throws FillingException {
        try {
            this.isLocked = LOCK.tryLock(10, TimeUnit.SECONDS);
            BigDecimal fuelBeforeRefueling = car.getFuel();
            LOGGER.info("Машина(" + car + ") подъехала к колонке (id = " + this.getId() + ")");

            if (isLocked) {
                BigDecimal finalAmount = car.getFuel().add(gasAmount);
                if (finalAmount.compareTo(Constant.MAX_CAR_FUEL) > 0 ||
                        this.fuel.subtract(gasAmount).compareTo(Constant.MIN_STATION_FUEL) < 0 || gasAmount.compareTo(Constant.MIN_STATION_FUEL) <= 0) {
                    throw new FillingException("В колонке не хватает бензина или введено некорректное значение бензина для заправки");
                }

                this.fuel = this.fuel.subtract(gasAmount);
                car.setFuel(finalAmount);
                Thread.sleep(3000);
                LOGGER.info(car.getCarMake().getMakeName() + "(id = " + car.getId() + ")" + " заправлена на колонке id = " + this.getId() + "! Топливо = " + car.getFuel() +
                        "%; Было заправлено " + car.getFuel().subtract(fuelBeforeRefueling) + "%");
            } else {
                LOGGER.info(car.getCarMake().getMakeName() + "(id = " + car.getId() + ") не дождалась своей очереди и уехала");
            }
        } catch (InterruptedException e) {
            throw new FillingException(e);
        } finally {
            if (this.isLocked) {
                LOCK.unlock();
            }
        }
    }

    public void fillFullTank(Car car) throws FillingException {
        try {
            BigDecimal fuelBeforeRefueling = car.getFuel();
            LOGGER.info("Машина(" + car + ") подъехала к колонке (id = " + this.getId() + ")");
            this.isLocked = LOCK.tryLock(10, TimeUnit.SECONDS);

            if (isLocked) {
                BigDecimal missingFuel = Constant.MAX_CAR_FUEL.subtract(car.getFuel());
                if (this.fuel.subtract(missingFuel).compareTo(Constant.MIN_STATION_FUEL) < 0) {
                    throw new FillingException("В колонке не хватает бензина");
                }

                this.fuel = this.fuel.subtract(missingFuel);
                car.setFuel(car.getFuel().add(missingFuel));
                Thread.sleep(3000);
                LOGGER.info(car.getCarMake().getMakeName() + "(id = " + car.getId() + ")" + " полностью заправлена на колонке id = " + this.getId() + "! Топливо = " + car.getFuel() +
                        "%; Было заправлено " + car.getFuel().subtract(fuelBeforeRefueling) + "%");
            } else {
                LOGGER.info(car.getCarMake().getMakeName() + "(id = " + car.getId() + ") не дождалась своей очереди и уехала");
            }
        } catch (InterruptedException e) {
            throw new FillingException(e);
        } finally {
            if (this.isLocked) {
                LOCK.unlock();
            }
        }
    }

    @Override
    public String toString() {
        return "FillingStation{" +
                "id=" + id +
                ", fuel=" + fuel + "%" +
                '}';
    }
}
