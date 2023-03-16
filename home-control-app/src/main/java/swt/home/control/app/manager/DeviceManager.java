package swt.home.control.app.manager;

public interface DeviceManager {
  void fetchValue();
  double getAverage();
  void performLogic(ManagerParameters parameters);
  void adjustParameters(ManagerParameters parameters);
}
