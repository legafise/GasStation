package com.lashkevich.station.constant;

import com.lashkevich.station.entity.CarMake;
import com.lashkevich.station.entity.Color;

import java.math.BigDecimal;

public class Constant {
    public static final BigDecimal MAX_CAR_FUEL = new BigDecimal("100.0");
    public static final BigDecimal MIN_CAR_FUEL = new BigDecimal("0.0");
    public static final BigDecimal MAX_STATION_FUEL = new BigDecimal("1000.0");
    public static final BigDecimal MIN_STATION_FUEL = new BigDecimal("0.0");
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final CarMake DEFAULT_CAR_MAKE = CarMake.BMW;
    public static final String GAS_STATION_BEFORE_OPEN_ENTER_MESSAGE = "Заправка до открытия";
    public static final String GAS_STATION_OPEN_ENTER_MESSAGE = "Заправка открыта";
    public static final String GAS_STATION_CLOSE_ENTER_MESSAGE = "Заправка закрыта";
    public static final String GAS_STATION_AFTER_CLOSE_ENTER_MESSAGE = "Заправка после закрытия";
    public static final String ENTERED_INCORRECT_VALUE_ENTER_MESSAGE = "введено некорректное значение!";
    public static final String CAR_ENTER_MESSAGE = "Машина";
    public static final String WENT_TO_FILLING_STATION_ENTER_MESSAGE = "подъехала к колонке";
    public static final String CAR_DID_NOT_WAIT_ENTER_MESSAGE = "не дождалась своей очереди и уехала";
    public static final String HAS_BEEN_REFUELED_ENTER_MESSAGE = "Было заправлено";
    public static final String FILED_ON_FILLING_STATION_ENTER_MESSAGE = "заправлена на колонке";
    public static final String ENTERED_INCORRECT_VALUE_IN_RANGE_ENTER_MESSAGE = "Введено недопустимое значение(введите число от 0 до 1000)";
    public static final String OR = "или";
    public static final String PERCENT = "%";
    public static final String SEMICOLON = ";";
    public static final String FUEL_GET_MESSAGE = "Топливо";
    public static final String EXCLAMATION_POINT = "!";
    public static final String NO_GAS_ENTER_MESSAGE = "В колонке не хватает бензина";
    public static final String ID = "id";
    public static final String EQUALS = "=";
    public static final String SPACE = " ";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String DASH = "-";
}
