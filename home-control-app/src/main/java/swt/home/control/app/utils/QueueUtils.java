package swt.home.control.app.utils;

import java.util.*;

public class QueueUtils {
  public static double getAverage(Queue<Double> queue){
    return queue.stream().mapToDouble(d -> d).average().orElse(0.0);
  }
}
