import swt.timer.beans.*;
import swt.timer.beans.impl.*;

module swt.timer.beans {
  exports swt.timer.beans;
  provides TimerProvider with SimpleTimerProvider, HighResTimerProvider;
}