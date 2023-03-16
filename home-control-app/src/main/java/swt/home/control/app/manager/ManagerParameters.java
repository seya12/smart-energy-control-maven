package swt.home.control.app.manager;

public class ManagerParameters {
  private double averageTemperature;
  private double averagePower;
  private double electricBoilerTemperature;

  public double getAverageTemperature() {
    return averageTemperature;
  }

  public void setAverageTemperature(double averageTemperature) {
    this.averageTemperature = averageTemperature;
  }

  public double getAveragePower() {
    return averagePower;
  }

  public void setAveragePower(double averagePower) {
    this.averagePower = averagePower;
  }

  public double getElectricBoilerTemperature() {
    return electricBoilerTemperature;
  }

  public void setElectricBoilerTemperature(double electricBoilerTemperature) {
    this.electricBoilerTemperature = electricBoilerTemperature;
  }

  @Override
  public String toString() {
    return "ManagerParameters{" +
           "averageTemperature=" + averageTemperature +
           ", averagePower=" + averagePower +
           ", electricBoilerTemperature=" + electricBoilerTemperature +
           '}';
  }
}
