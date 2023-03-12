package swt.timer.beans;

import swt.timer.beans.impl.*;

public class TimerFactory {
  public static Timer createTimer(int interval, int numTicks) {
    return new SimpleTimer(interval, numTicks);
  }
}
