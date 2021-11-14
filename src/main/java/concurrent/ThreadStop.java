package concurrent;

import com.sun.source.doctree.ThrowsTree;

/**
 * @author arvikv
 * @version 1.0
 * @since 14.11.2021
 * * Лямбда используется чтобы не создавать анонимный класс внутри потока
 *  * NEW - нить создана, но не запущена.
 *  * RUNNABLE - нить запущена и выполняется.
 *  * BLOCKED - нить заблокирована.
 *  * WAITING - нить ожидает уведомления.
 *  * TIMED_WAITING - нить ожидает уведомление в течении определенного периода.
 *  * TERMINATED - нить завершила работу.
 */


public class ThreadStop {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(
            () -> {
              int count = 0;
              while (!Thread.currentThread().isInterrupted()) {
                System.out.println(count++);
              }
            }
    );
    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}
