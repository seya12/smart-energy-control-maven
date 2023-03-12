package swt.noise.generator.impl;

import swt.noise.generator.*;

public class NoiseApiImpl implements NoiseApi {
  @Override
  public double noise(double begin, double end) {
    return Math.random() * (end - begin) + begin;
  }
}
