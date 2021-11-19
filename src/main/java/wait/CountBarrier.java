package wait;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 */
public class CountBarrier {
    private final Object monitor = this;
    /**
     * Переменная total содержит количество вызовов метода count().
     */
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Метод count изменяет состояние программы. Это значит, что внутри метода count нужно вызывать метод notifyAll.
     */
    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    /**
     * Нити, которые выполняют метод await, могут начать работу если поле count >= total.
     * Если оно не равно, то нужно перевести нить в состояние wait.
     */
    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rcount " + i);
                            countBarrier.count();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "FIRST"
        );
        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "SECOND"
        );
        first.start();
        second.start();
    }
}
