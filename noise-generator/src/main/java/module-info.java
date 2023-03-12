import swt.noise.generator.*;
import swt.noise.generator.impl.*;

module swt.noise.generator {
  requires jnoise.pipeline;
  requires jnoise.generators;
  requires jnoise.core;
  requires jnoise.modules;

  exports swt.noise.generator;
  provides NoiseApiProvider with NoiseApiProviderImpl;
}