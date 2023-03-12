package swt.timer.beans.impl;

import swt.timer.beans.*;


public class SimpleTimerProvider implements TimerProvider {
  @Override
  public double getResolution() {
    return 1 / 1000.0;
  }

  @Override
  public Timer createTimer(int interval, int numTicks) {
    return new SimpleTimer(interval, numTicks);
  }
}
