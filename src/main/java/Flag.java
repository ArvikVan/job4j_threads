/**
 * @author arvikv
 * @version 1.0
 * @since 16.11.2021
 * Может возникнуть ситуация, что главная нить запишет новое значение переменной в кеш процессора,
 * а дополнительная нить будет продолжать читать переменную flag из регистра.
 * Эта ситуация называется проблемой видимости (share visibility problem).
 */
public class Flag {
    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (flag) {
                        System.out.println(Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        flag = false;
        thread.join();
    }
}
