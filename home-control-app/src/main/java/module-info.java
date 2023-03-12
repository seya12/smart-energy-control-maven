import swt.ac.control.*;
import swt.balcony.powerplant.*;
import swt.electric.boiler.*;
import swt.timer.beans.*;

module swt.home.control.app {
  requires swt.timer.beans;
  requires swt.balcony.powerplant;
  requires swt.ac.control;
  requires swt.electric.boiler;

  uses TimerProvider;
  uses InverterApiProvider;
  uses AirConditionApiProvider;
  uses ElectricBoilerApiProvider;
}