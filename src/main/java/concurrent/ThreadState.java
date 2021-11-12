package concurrent;

import org.quartz.spi.ThreadExecutor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author arvikv
 * @version 1.0
 * @since 12.11.2021
 */

public class ThreadState {
  /**
   * последовательно вызываем new, runnable, terminated
   * @param args на входе
   *             getState() возвращает состояние потока в данный момент
   */
  public static void main(String[] args) {
    Thread first = new Thread(
            () -> System.out.println("first " + Thread.currentThread().getName())
    );
    first.start();
    Thread second = new Thread(
            () -> System.out.println("second " + Thread.currentThread().getName())
    );
    second.start();

    while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
      System.out.println("first.getState() " + first.getState());
      System.out.println("second.getState() " + second.getState());
    }
    System.out.println("the end " + Thread.currentThread().getName());
  }
}
