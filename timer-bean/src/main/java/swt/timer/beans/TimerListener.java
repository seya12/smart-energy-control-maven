package swt.timer.beans;

@FunctionalInterface
public interface TimerListener {
    void expired(TimerEvent event);
}
