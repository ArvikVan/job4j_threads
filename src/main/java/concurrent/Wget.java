package concurrent;

/**
 * @author arvikv
 * @version 1.0
 * @since 14.11.2021
 * Лямбда используется чтобы не создавать анонимный класс внутри потока
 * NEW - нить создана, но не запущена.
 * RUNNABLE - нить запущена и выполняется.
 * BLOCKED - нить заблокирована.
 * WAITING - нить ожидает уведомления.
 * TIMED_WAITING - нить ожидает уведомление в течении определенного периода.
 * TERMINATED - нить завершила работу.
 */


public class Wget {
  public static void main(String[] args) {
    Thread thread = new Thread(
            () -> {
              try {
                for (int i = 0; i <= 100; i++) {
                  System.out.print("\rLoading: " + i + "%");
                  Thread.sleep(1000);
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
    );
    thread.start();

  }
}
