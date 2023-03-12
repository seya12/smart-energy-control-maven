package swt.timer.beans;

import java.util.*;

public class TimerEvent extends EventObject {

    private final int tickCount;
    private final int numTicks;

    public TimerEvent(Timer timer, int tickCount, int numTicks) {
        super(timer);
        this.tickCount = tickCount;
        this.numTicks = numTicks;
    }

    public int getNumTicks() {
        return numTicks;
    }

    public int getTickCount() {
        return tickCount;
    }

    @Override
    public Timer getSource() {
        return (Timer) super.getSource();
    }
}
