import swt.noise.generator.*;
import swt.noise.generator.impl.*;

module swt.noise.generator {
  exports swt.noise.generator;
  provides NoiseApiProvider with NoiseApiProviderImpl;
}