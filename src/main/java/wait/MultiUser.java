package wait;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 * Пока нить master не выполнит метод on, нить slave не начнет работу.
 */
public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                }, "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "Slave"
        );
        master.start();
        slave.start();
    }
}
