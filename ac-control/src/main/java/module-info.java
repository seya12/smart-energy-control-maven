import swt.ac.control.*;
import swt.ac.control.impl.*;
import swt.noise.generator.*;

module swt.ac.control {
  requires swt.noise.generator;
  uses NoiseApiProvider;
  requires org.slf4j;

  exports swt.ac.control;
  provides AirConditionApiProvider with AirConditionApiProviderImpl;
}