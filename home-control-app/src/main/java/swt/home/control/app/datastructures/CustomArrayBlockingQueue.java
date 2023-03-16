package swt.home.control.app.datastructures;

import java.util.concurrent.*;

public class CustomArrayBlockingQueue<E> extends ArrayBlockingQueue<E> {

  public CustomArrayBlockingQueue() {
    super(10);
  }

  public CustomArrayBlockingQueue(int capacity) {
    super(capacity);
  }


  @Override
  public boolean add(E e) {
    if (!super.offer(e)) { //tries to add to the tail of the queue. returns false if queue is full
      super.remove();
      return super.add(e);
    }
    return true;
  }
}
