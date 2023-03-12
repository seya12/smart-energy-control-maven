package swt.ac.control.impl;

import swt.ac.control.*;
import swt.noise.generator.*;

import java.util.*;

public class AirConditionApiImpl implements AirConditionApi {
  private boolean running = false;
  private final NoiseApi noiseApi;

  public AirConditionApiImpl() {
    noiseApi = ServiceLoader.load(NoiseApiProvider.class)
      .findFirst()
      .orElseThrow()
      .createNoiseApi();
  }

  @Override
  public void turnOn() {
    if (this.running) {
      System.out.println("AC already running");
      return;
    }
    this.running = true;
    System.out.println("Switched AC on");
  }

  @Override
  public void turnOff() {
    if (!this.running) {
      System.out.println("AC already switched off");
      return;
    }
    this.running = false;
    System.out.println("Switched AC off");
  }

  @Override
  public double getRoomTemperature() {
    return noiseApi.noise(19.0, 30.0); //between 19.0 and 30.0
  }
}
