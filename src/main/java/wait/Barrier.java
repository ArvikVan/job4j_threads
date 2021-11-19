package wait;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 */


public class Barrier {
  /**
   * Переменная flag - это общий ресурс, поэтому мы с ней работаем  только в  критической секции.
   */
    private boolean flag = false;
    private final Object monitor = this;

  /**
   * Метод on и off меняют флаг с true на false. После каждого изменения программа будит нити, которые ждут изменений.
   */
    public void on() {
      synchronized (monitor) {
        flag = true;
        monitor.notifyAll();
      }
    }

  /**
   * Метод on и off меняют флаг с true на false. После каждого изменения программа будит нити, которые ждут изменений.
   */
    public void off() {
      synchronized (monitor) {
        flag = false;
        monitor.notifyAll();
      }
    }

  /**
   * Когда нить заходит в метод check, то она проверяет flag. Если флаг = false, то нить засыпает.
   * Когда другая нить выполни метод on или off, то у объекта монитора выполняется метод notifyAll.
   * Метод notifyAll переводить все нити из состояния wait в runnable.
   */
    public void check() {
      synchronized (monitor) {
        while (!flag) {
          try {
            monitor.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      }
    }

}
