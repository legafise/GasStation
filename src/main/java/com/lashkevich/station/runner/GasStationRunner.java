package com.lashkevich.station.runner;

import com.lashkevich.station.constant.Constant;
import com.lashkevich.station.entity.GasStation;
import com.lashkevich.station.invoker.TestInvoker;
import com.lashkevich.station.util.randomizer.CarRandomizer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GasStationRunner {
    private static final Logger LOGGER = Logger.getLogger(TestInvoker.class.getName());

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info(Constant.GAS_STATION_BEFORE_OPEN_ENTER_MESSAGE + Constant.SPACE + Constant.DASH + Constant.SPACE + GasStation.getInstance());
        LOGGER.info(Constant.GAS_STATION_OPEN_ENTER_MESSAGE + Constant.EXCLAMATION_POINT);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            threads.add(CarRandomizer.generateRandomCar());
        }

        startThreads(threads);
        joinThreads(threads);

        LOGGER.info(Constant.GAS_STATION_CLOSE_ENTER_MESSAGE + Constant.EXCLAMATION_POINT);
        LOGGER.info(Constant.GAS_STATION_AFTER_CLOSE_ENTER_MESSAGE + Constant.SPACE + Constant.DASH + Constant.SPACE + GasStation.getInstance());
    }

    private static void startThreads(List<Thread> threads) {
        threads.forEach(Thread::start);
    }

    private static void joinThreads(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
