package swt.home.control.app.manager.impl;

import org.slf4j.*;
import swt.electric.boiler.*;
import swt.home.control.app.datastructures.*;
import swt.home.control.app.manager.*;
import swt.home.control.app.utils.*;

import java.util.*;

public class ElectricBoilerManager implements DeviceManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElectricBoilerManager.class);
  private final ElectricBoilerApi electricBoilerApi;
  private final CustomArrayBlockingQueue<Double> boilerTemperature;

  public ElectricBoilerManager() {
    electricBoilerApi = ServiceLoader.load(ElectricBoilerApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createElectricBoilerApi();

    boilerTemperature = new CustomArrayBlockingQueue<>(1); //currently only one value should be checked
  }

  @Override
  public void fetchValue() {
    boilerTemperature.add(electricBoilerApi.getBoilerTemperature());
    LOGGER.trace("Current values of boiler: {}", boilerTemperature);
  }

  @Override
  public double getAverage() {
    return QueueUtils.getAverage(boilerTemperature);
  }

  @Override
  public void performLogic(ManagerParameters parameters) {
    LOGGER.trace("Starting performLogic...");

    boolean enoughPowerForBoiler = parameters.getAveragePower() > 0.4;
    boolean boilerTooCold = parameters.getElectricBoilerTemperature() < 40;

    if (enoughPowerForBoiler && boilerTooCold) {
      electricBoilerApi.turnOn();
    } else{
      LOGGER.trace("No action executed...");
    }
  }

  @Override
  public void adjustParameters(ManagerParameters parameters) {
    parameters.setElectricBoilerTemperature(getAverage());
  }
}