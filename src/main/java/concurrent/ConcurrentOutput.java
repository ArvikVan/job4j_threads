package concurrent;

/**
 * @author arvikv
 * @version 1.0
 * @since 12.11.2021
 */


public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println("----");
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
