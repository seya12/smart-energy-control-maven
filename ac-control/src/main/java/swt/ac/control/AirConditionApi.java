package swt.ac.control;

public interface AirConditionApi {
  //switches AC ON or OFF
  void turnOn();

  void turnOff();

  //current room temperature in Celsius (-10.0 to +90)
  double getRoomTemperature();
}
