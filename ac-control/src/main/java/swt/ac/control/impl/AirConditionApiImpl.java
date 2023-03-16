package swt.ac.control.impl;

import org.slf4j.*;
import swt.ac.control.*;
import swt.noise.generator.*;

import java.util.*;

public class AirConditionApiImpl implements AirConditionApi {
  private static final Logger LOGGER = LoggerFactory.getLogger(AirConditionApiImpl.class);

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
      LOGGER.debug("AC already running");
      return;
    }
    this.running = true;
    LOGGER.info("Switched AC on");
  }

  @Override
  public void turnOff() {
    if (!this.running) {
      LOGGER.debug("AC already switched off");
      return;
    }
    this.running = false;
    LOGGER.info("Switched AC off");
  }

  @Override
  public double getRoomTemperature() {
    return noiseApi.noise(19.0, 30.0); //between 19.0 and 30.0
  }
}
