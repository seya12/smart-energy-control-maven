package swt.balcony.powerplant.impl;

import swt.balcony.powerplant.*;
import swt.noise.generator.*;

import java.util.*;

public class InverterApiImpl implements InverterApi {
  private final NoiseApi noiseApi;

  public InverterApiImpl() {
    noiseApi = ServiceLoader.load(NoiseApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createNoiseApi();
  }

  @Override
  public double getActualCurrent() {
    return noiseApi.noise(0.0, 0.8);
  }
}
