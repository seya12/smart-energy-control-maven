package swt.home.control.app.manager.impl;

import org.slf4j.*;
import swt.ac.control.*;
import swt.home.control.app.datastructures.*;
import swt.home.control.app.manager.*;
import swt.home.control.app.utils.*;

import java.util.*;

public class AirConditionManager implements DeviceManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(AirConditionManager.class);
  private final AirConditionApi airConditionApi;
  private final CustomArrayBlockingQueue<Double> currentTemperatures;

  public AirConditionManager() {
    airConditionApi = ServiceLoader.load(AirConditionApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createAirConditionApi();

    currentTemperatures = new CustomArrayBlockingQueue<>(3);
  }

  @Override
  public void fetchValue() {
    currentTemperatures.add(airConditionApi.getRoomTemperature());
    LOGGER.trace("Current values of air condition: {}", currentTemperatures);
  }

  @Override
  public double getAverage() {
    return QueueUtils.getAverage(currentTemperatures);
  }

  @Override
  public void performLogic(ManagerParameters parameters) {
    LOGGER.trace("Starting performLogic...");

    boolean turnAirConditionOff = parameters.getAverageTemperature() < 22;
    boolean roomTemperatureTooWarm = parameters.getAverageTemperature() > 24;
    boolean enoughPowerForAC = parameters.getAveragePower() > 0.1;

    if (turnAirConditionOff) {
      airConditionApi.turnOff();
    } else if (roomTemperatureTooWarm && enoughPowerForAC) {
      airConditionApi.turnOn();
    } else {
      LOGGER.trace("No action executed...");
    }
  }

  @Override
  public void adjustParameters(ManagerParameters parameters) {
    parameters.setAverageTemperature(getAverage());
  }
}
