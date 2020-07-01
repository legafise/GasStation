package com.lashkevich.station.util.randomizer;

import java.math.BigDecimal;

public class BigDecimalRandomizer {
    public static BigDecimal generateRandomBigDecimal(BigDecimal min, BigDecimal max) {
        return min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min))).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
