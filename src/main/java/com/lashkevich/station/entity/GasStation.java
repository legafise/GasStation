package com.lashkevich.station.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GasStation {
    private static final GasStation GAS_STATION = new GasStation();
    private List<FillingStation> fillingStations;

    private GasStation() {
        fillingStations = new ArrayList<>();
        fillingStations.add(new FillingStation());
        fillingStations.add(new FillingStation(new BigDecimal("500")));
    }

    public static GasStation getInstance() {
        return GAS_STATION;
    }

    public List<FillingStation> getFillingStations() {
        return fillingStations;
    }

    public void setFillingStations(List<FillingStation> fillingStations) {
        this.fillingStations = fillingStations;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "fillingStations=" + fillingStations +
                '}';
    }
}
