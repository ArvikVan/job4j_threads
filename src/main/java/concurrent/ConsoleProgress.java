package concurrent;

/**
 * @author arvikv
 * @version 1.0
 * @since 14.11.2021
 *
 */


public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progres = new Thread(new ConsoleProgress());
        progres.start();
        Thread.sleep(1000);
        progres.interrupt();
    }

    @Override
    public void run() {
        String[] s = {"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
          try {
              for (String s1 : s) {
                  Thread.sleep(500);
                  System.out.print("\rload: " + s1);
              }
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          }
        }
    }
}
