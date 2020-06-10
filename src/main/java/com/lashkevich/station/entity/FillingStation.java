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
    private Lock lock = new ReentrantLock();
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
            throw new FillingException(Constant.ENTERED_INCORRECT_VALUE_ENTER_MESSAGE);
        }

        this.fuel = fuel;
    }

    public void fillTank(Car car, BigDecimal gasAmount) throws FillingException {
        try {
            this.isLocked = lock.tryLock(10, TimeUnit.SECONDS);
            BigDecimal fuelBeforeRefueling = car.getFuel();
            logCarWentFillingStation(car, this.getId());

            if (isLocked) {
                BigDecimal finalAmount = car.getFuel().add(gasAmount);
                if (finalAmount.compareTo(Constant.MAX_CAR_FUEL) > 0 ||
                        this.fuel.subtract(gasAmount).compareTo(Constant.MIN_STATION_FUEL) < 0 || gasAmount.compareTo(Constant.MIN_STATION_FUEL) <= 0) {
                    throw new FillingException(Constant.NO_GAS_ENTER_MESSAGE + Constant.SPACE + Constant.OR + Constant.SPACE +
                            Constant.ENTERED_INCORRECT_VALUE_ENTER_MESSAGE);
                }

                this.fuel = this.fuel.subtract(gasAmount);
                car.setFuel(finalAmount);
                Thread.sleep(2000);
                logCarRefuelingInfo(car, fuelBeforeRefueling, this.id);
            } else {
                LOGGER.info(car.getCarMake().getMakeName() + Constant.LEFT_BRACKET + Constant.ID + Constant.SPACE + Constant.EQUALS +
                        Constant.SPACE + car.getId() + Constant.RIGHT_BRACKET + Constant.SPACE + Constant.CAR_DID_NOT_WAIT_ENTER_MESSAGE);
            }
        } catch (InterruptedException e) {
            throw new FillingException(e);
        } finally {
            if (this.isLocked) {
                lock.unlock();
            }
        }
    }

    public void fillFullTank(Car car) throws FillingException {
        try {
            this.isLocked = lock.tryLock(10, TimeUnit.SECONDS);
            BigDecimal fuelBeforeRefueling = car.getFuel();
            logCarWentFillingStation(car, this.getId());

            if (isLocked) {
                BigDecimal missingFuel = Constant.MAX_CAR_FUEL.subtract(car.getFuel());
                if (this.fuel.subtract(missingFuel).compareTo(Constant.MIN_STATION_FUEL) < 0) {
                    throw new FillingException(Constant.NO_GAS_ENTER_MESSAGE);
                }

                this.fuel = this.fuel.subtract(missingFuel);
                car.setFuel(car.getFuel().add(missingFuel));
                Thread.sleep(3000);
                logCarRefuelingInfo(car, fuelBeforeRefueling, this.id);
            } else {
                logCarLeaveInfo(car);
            }
        } catch (InterruptedException e) {
            throw new FillingException(e);
        } finally {
            if (this.isLocked) {
                lock.unlock();
            }
        }
    }

    private static void logCarRefuelingInfo(Car car, BigDecimal fuelBeforeRefueling, long id) {
        LOGGER.info(car.getCarMake().getMakeName() + Constant.LEFT_BRACKET + Constant.ID + Constant.SPACE + Constant.EQUALS +
                Constant.SPACE + car.getId() + Constant.RIGHT_BRACKET + Constant.SPACE + Constant.FILED_ON_FILLING_STATION_ENTER_MESSAGE +
                Constant.SPACE + Constant.ID + Constant.SPACE + Constant.EQUALS + Constant.SPACE + id +
                Constant.EXCLAMATION_POINT + Constant.SPACE + Constant.FUEL_GET_MESSAGE + Constant.SPACE + Constant.EQUALS +
                Constant.SPACE + car.getFuel() + Constant.PERCENT + Constant.SEMICOLON + Constant.SPACE + Constant.HAS_BEEN_REFUELED_ENTER_MESSAGE +
                Constant.SPACE + car.getFuel().subtract(fuelBeforeRefueling) + Constant.PERCENT);
    }

    private static void logCarLeaveInfo(Car car) {
        LOGGER.info(car.getCarMake().getMakeName() + Constant.LEFT_BRACKET + Constant.ID + Constant.SPACE + Constant.EQUALS +
                Constant.SPACE + car.getId() + Constant.RIGHT_BRACKET + Constant.SPACE + Constant.CAR_DID_NOT_WAIT_ENTER_MESSAGE);
    }

    private static void logCarWentFillingStation(Car car, long id) {
        LOGGER.info(Constant.CAR_ENTER_MESSAGE + Constant.LEFT_BRACKET + car + Constant.RIGHT_BRACKET + Constant.SPACE +
                Constant.WENT_TO_FILLING_STATION_ENTER_MESSAGE + Constant.SPACE + Constant.LEFT_BRACKET + Constant.ID +
                Constant.EQUALS + id + Constant.RIGHT_BRACKET);
    }

    @Override
    public String toString() {
        return "FillingStation{" +
                "id=" + id +
                ", fuel=" + fuel + "%" +
                '}';
    }
}
