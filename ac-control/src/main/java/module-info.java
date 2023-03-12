import swt.ac.control.*;
import swt.ac.control.impl.*;
import swt.noise.generator.*;

module swt.ac.control {
  requires swt.noise.generator;
  uses NoiseApiProvider;

  exports swt.ac.control;
  provides AirConditionApiProvider with AirConditionApiProviderImpl;
}