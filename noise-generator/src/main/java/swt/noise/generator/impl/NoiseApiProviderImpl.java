package swt.noise.generator.impl;

import swt.noise.generator.*;

public class NoiseApiProviderImpl implements NoiseApiProvider {
  @Override
  public NoiseApi createNoiseApi() {
    return new NoiseApiImpl();
  }
}
