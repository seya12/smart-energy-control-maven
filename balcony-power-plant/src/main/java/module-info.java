import swt.balcony.powerplant.*;
import swt.balcony.powerplant.impl.*;
import swt.noise.generator.*;

module swt.balcony.powerplant {
  requires swt.noise.generator;
  uses NoiseApiProvider;

  exports swt.balcony.powerplant;
  provides InverterApiProvider with InverterApiProviderImpl;
}