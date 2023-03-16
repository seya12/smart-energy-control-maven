package swt.electric.boiler.impl;

import org.slf4j.*;
import swt.electric.boiler.*;
import swt.noise.generator.*;

import java.util.*;

public class ElectricBoilerApiImpl implements ElectricBoilerApi {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElectricBoilerApiImpl.class);

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
      LOGGER.debug("Boiler already running");
      return;
    }
    this.running = true;
    LOGGER.info("Switched boiler on");
  }

  @Override
  public double getBoilerTemperature() {
    return noiseApi.noise(0.0, 100.0);
  }
}
