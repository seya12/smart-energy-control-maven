package swt.home.control.app;

import swt.ac.control.*;
import swt.balcony.powerplant.*;
import swt.electric.boiler.*;
import swt.timer.beans.*;
import swt.timer.beans.Timer;

import java.util.*;

public class HomeManager {

  private final Timer timer;
  private final InverterApi inverterApi;
  private final AirConditionApi airConditionApi;
  private final ElectricBoilerApi electricBoilerApi;
  private final Queue<Double> currentTemperatures;
  private final Queue<Double> producedPower;


  public HomeManager() {
    timer = ServiceLoader.load(TimerProvider.class)
      .findFirst()
      .orElseThrow()
      .createTimer(1000, 1000);

    inverterApi = ServiceLoader.load(InverterApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createInverterApi();

    airConditionApi = ServiceLoader.load(AirConditionApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createAirConditionApi();

    electricBoilerApi = ServiceLoader.load(ElectricBoilerApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createElectricBoilerApi();

    currentTemperatures = new CustomArrayBlockingQueue<>(3);
    producedPower = new CustomArrayBlockingQueue<>(3);
  }

  public void start() {
    timer.addTimerListener(this::performLogic);
    timer.start();
  }

  private void performLogic(TimerEvent timerEvent) {
    //System.out.println("Step "+timerEvent.getTickCount());
    currentTemperatures.add(airConditionApi.getRoomTemperature());
    producedPower.add(inverterApi.getActualCurrent());

    var avgTemp = currentTemperatures.stream().mapToDouble(d -> d).average().orElse(0.0);
    var avgPower = producedPower.stream().mapToDouble(d -> d).average().orElse(0.0);
    System.out.println("Avg Temp: " + avgTemp + "List: " + currentTemperatures);
    System.out.println("Avg Power: " + avgPower + "List: " + producedPower);

    boolean turnAirConditionOff = avgTemp < 22;
    boolean roomTemperatureTooWarm = avgTemp > 24;
    boolean enoughPowerForAC = avgPower > 0.1;
    boolean enoughPowerForBoiler = avgPower > 0.4;
    boolean boilerTooCold = electricBoilerApi.getBoilerTemperature() < 40;

    if (turnAirConditionOff) {
      airConditionApi.turnOff();
    } else if (roomTemperatureTooWarm && enoughPowerForAC) {
      airConditionApi.turnOn();
    } else {
      System.out.println("Nothing changed");
    }

    if (enoughPowerForBoiler && boilerTooCold) {
      electricBoilerApi.turnOn();
    }
  }

  public static void main(String[] args) {
    HomeManager manager = new HomeManager();
    manager.start();
  }
}
