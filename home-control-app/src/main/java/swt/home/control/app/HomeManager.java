package swt.home.control.app;

import org.slf4j.*;
import swt.home.control.app.manager.*;
import swt.home.control.app.manager.impl.*;
import swt.timer.beans.*;
import swt.timer.beans.Timer;

import java.util.*;

public class HomeManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomeManager.class);

  private final Timer timer;
  private final List<DeviceManager> deviceManagers;

  public HomeManager() {
    timer = ServiceLoader.load(TimerProvider.class)
      .findFirst()
      .orElseThrow()
      .createTimer(5000, 1000);

    deviceManagers = List.of(new AirConditionManager(), new InverterManager(), new ElectricBoilerManager());
  }

  public static void main(String[] args) {
    HomeManager manager = new HomeManager();
    manager.start();
  }

  public void start() {
    timer.addTimerListener(this::addTimerAction);
    timer.start();
  }

  private void addTimerAction(TimerEvent timerEvent) {
    LOGGER.trace("Step: {}", timerEvent.getTickCount());

    var params = createParam();
    LOGGER.debug("Fetched parameters - current value:: {}", params);

    performLogic(params);
  }

  private ManagerParameters createParam() {
    ManagerParameters params = new ManagerParameters();
    for (DeviceManager deviceManager : deviceManagers) {
      deviceManager.fetchValue();
      deviceManager.adjustParameters(params);
    }
    return params;
  }

  private void performLogic(ManagerParameters params) {
    for (DeviceManager deviceManager : deviceManagers) {
      deviceManager.performLogic(params);
    }
  }
}
