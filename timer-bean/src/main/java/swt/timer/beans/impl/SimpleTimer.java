package swt.timer.beans.impl;

import swt.timer.beans.Timer;
import swt.timer.beans.*;

import java.util.*;
import java.util.concurrent.*;

public class SimpleTimer implements Timer {
  private int interval;
  private int numTicks;
  private Thread tickerThread;

  private final List<TimerListener> listeners =
    new CopyOnWriteArrayList<>();

  public SimpleTimer(int interval, int numTicks) {
    this.interval = interval;
    this.numTicks = numTicks;
  }

  @Override
  public synchronized void start() {
    if (isRunning()) {
      return;
    }
    final int interval = this.getInterval();
    final int numTicks = this.getNumTicks();

    tickerThread = new Thread(() -> {
      //THREAD START
      int tickCount = 0;
      while (tickCount < numTicks &&
             !Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(interval);
          fireEvent(new TimerEvent(this, tickCount, numTicks));
          tickCount++;
        } catch (InterruptedException e) {
          //restore interruption flag
          Thread.currentThread().interrupt();
        }
      }
      synchronized (this) {
        tickerThread = null;
      }
      //THREAD END
    });
    tickerThread.start();
  }

  private void fireEvent(TimerEvent event) {
    listeners.forEach(listener -> listener.expired(event));
  }

  @Override
  public synchronized void stop() {
    if (tickerThread != null) {
      tickerThread.interrupt();
      tickerThread = null;
    }
  }

  @Override
  public synchronized boolean isRunning() {
    return tickerThread != null;
  }

  @Override
  public int getInterval() {
    return interval;
  }

  @Override
  public int getNumTicks() {
    return numTicks;
  }

  @Override
  public void addTimerListener(TimerListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeTimerListener(TimerListener listener) {
    listeners.remove(listener);
  }
}
