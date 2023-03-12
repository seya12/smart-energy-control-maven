package swt.electric.boiler;

public interface ElectricBoilerApi {
  //switches boiler on
  void turnOn();

  //current boiler temperature in degrees Celsius (0.0 to +100.0)
  double getBoilerTemperature();
}
