package swt.electric.boiler.impl;

import swt.electric.boiler.*;
import swt.noise.generator.*;

import java.util.*;

public class ElectricBoilerApiImpl implements ElectricBoilerApi {
  private boolean running;
  private final NoiseApi noiseApi;

  public ElectricBoilerApiImpl() {
    noiseApi = ServiceLoader.load(NoiseApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createNoiseApi();
  }

  @Override
  public void turnOn() {
    if (this.running) {
      System.out.println("Boiler already running");
      return;
    }
    this.running = true;
    System.out.println("Switched boiler on");
  }

  @Override
  public double getBoilerTemperature() {
    return noiseApi.noise(0.0, 100.0);
  }
}
