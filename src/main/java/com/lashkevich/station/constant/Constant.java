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
}
