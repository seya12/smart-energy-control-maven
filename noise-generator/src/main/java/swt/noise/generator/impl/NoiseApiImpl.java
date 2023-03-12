package swt.noise.generator.impl;

import de.articdive.jnoise.generators.noise_parameters.fade_functions.*;
import de.articdive.jnoise.generators.noise_parameters.interpolation.*;
import de.articdive.jnoise.modules.octavation.fractal_functions.*;
import de.articdive.jnoise.pipeline.*;
import swt.noise.generator.*;

import java.util.*;

public class NoiseApiImpl implements NoiseApi {
  private double counter;
  private static final int SEED =3301;

  /*
  alternative:
      noiseGenerator = JNoise.newBuilder()
      .perlin(3301, Interpolation.LINEAR, FadeFunction.NONE)
      .scale(1 / 16.0)
      .addModifier(v -> (v + 1) / 2.0 * (max - min) + min)
      .clamp(0, 1.0)
      .build();
    return map(noiseValue, 0, 1, min, max);
   */

  @Override
  public double noise(double min, double max) {
    //gives me best values in range
    JNoise noiseGenerator = JNoise.newBuilder()
      .perlin(3301, Interpolation.COSINE, FadeFunction.IMPROVED_PERLIN_NOISE)
      .addModifier(v -> (v + 1) / 2.0 * (max - min) + min)
      .octavate(2, 1.55, 1.0, FractalFunction.FBM, false) //gives me best values in range
      .build();

    return noiseGenerator.evaluateNoise(Math.random() * 3301);
  }

  //https://stackoverflow.com/questions/17134839/how-does-the-map-function-in-processing-work
  private static double map(double value,
                                double min1,
                                double max1,
                                double min2,
                                double max2) {
    return min2 + (max2 - min2) * ((value - min1) / (max1 - min1));
  }
}
