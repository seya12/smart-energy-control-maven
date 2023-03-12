package swt.timer.beans.impl;


import swt.timer.beans.*;

public class HighResTimerProvider implements TimerProvider {
    @Override
    public double getResolution() {
        return 1 / 1000_000.0;
    }

    @Override
    public Timer createTimer(int interval, int numTicks) {
        return new SimpleTimer(interval, numTicks);
    }
}
