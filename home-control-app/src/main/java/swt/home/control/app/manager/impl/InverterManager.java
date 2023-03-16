package swt.home.control.app.manager.impl;

import org.slf4j.*;
import swt.balcony.powerplant.*;
import swt.home.control.app.datastructures.*;
import swt.home.control.app.manager.*;
import swt.home.control.app.utils.*;

import java.util.*;

public class InverterManager implements DeviceManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(InverterManager.class);

  private final InverterApi inverterApi;
  private final CustomArrayBlockingQueue<Double> producedPower;

  public InverterManager() {
    inverterApi = ServiceLoader.load(InverterApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createInverterApi();

    producedPower = new CustomArrayBlockingQueue<>(3);
  }

  @Override
  public void fetchValue() {
    producedPower.add(inverterApi.getActualCurrent());
    LOGGER.trace("Current values of inverter: {}", producedPower);
  }

  @Override
  public double getAverage() {
    return QueueUtils.getAverage(producedPower);
  }

  @Override
  public void performLogic(ManagerParameters parameters) {
    LOGGER.trace("No logic in inverter manager...");
  }

  @Override
  public void adjustParameters(ManagerParameters parameters) {
    parameters.setAveragePower(getAverage());
  }
}
