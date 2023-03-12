package swt.timer.beans;

public interface TimerProvider {
    double getResolution();

    Timer createTimer(int interval, int numTicks);
}
