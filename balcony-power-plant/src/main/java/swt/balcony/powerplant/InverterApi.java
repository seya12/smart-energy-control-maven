package swt.balcony.powerplant;

public interface InverterApi {
  // currently produced power in kWh with 1/1000 accuracy
  // (0,001 = 1  // watt hour, 1,000 = 1 kilowatt hour)
  double getActualCurrent();
}
