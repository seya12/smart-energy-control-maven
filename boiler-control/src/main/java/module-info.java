import swt.electric.boiler.*;
import swt.electric.boiler.impl.*;
import swt.noise.generator.*;

module swt.electric.boiler {
  requires swt.noise.generator;
  uses NoiseApiProvider;

  exports swt.electric.boiler;
  provides ElectricBoilerApiProvider with ElectricBoilerApiProviderImpl;
}