package swt.electric.boiler.impl;

import swt.electric.boiler.*;

public class ElectricBoilerApiProviderImpl implements ElectricBoilerApiProvider {
  @Override
  public ElectricBoilerApi createElectricBoilerApi() {
    return new ElectricBoilerApiImpl();
  }
}
