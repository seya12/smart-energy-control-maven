package swt.ac.control.impl;

import swt.ac.control.*;

public class AirConditionApiProviderImpl implements
                                         AirConditionApiProvider {
  @Override
  public AirConditionApi createAirConditionApi() {
    return new AirConditionApiImpl();
  }
}
