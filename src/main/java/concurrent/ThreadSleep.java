package concurrent;

import org.quartz.spi.ThreadExecutor;

/**
 * @author arvikv
 * @version 1.0
 * @since 14.11.2021
 */


public class ThreadSleep {
  public static void main(String[] args) {
    Thread thread = new Thread(
            () -> {
              try {
                System.out.println("Start loading... ");
                Thread.sleep(3000);
                System.out.println("Loaded.");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
    );
    thread.start();
    System.out.println("Main");
  }
}
