package swt.balcony.powerplant.impl;

import swt.balcony.powerplant.*;

public class InverterApiProviderImpl implements InverterApiProvider {
  @Override
  public InverterApi createInverterApi() {
    return new InverterApiImpl();
  }
}
