package pool;

import bbq.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ArvikV
 * @version 1.2
 * @since 23.11.2021
 * 1.1
 * 1. Это должно быть быть в конструкторе
 * и при этом каждую нить надо запустить
 * 2. Добавьте хотя бы main() и проверьте работу пула
 * 1.2 thread.join(1000); Это уберите. Из цикла запусков нитей
 */
public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    /**
     * добавляем задачи tasks.offer(job); потоков в кол-ве size согласно условию
     * Проверяем состояние флага Thread.currentThread().isInterrupted()
     * возвращаем объект из внутренней коллекции
     * заполняем жобами блок.очередь
     * и при этом каждую нить надо запустить
     */
    public ThreadPool() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(
                    () -> {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                tasks.poll().run();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
            ));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * метод добавляет задачи в блокирующую очередь tasks
     * @param job на входе задача которую надо добавить в блок.очередь
     * @throws InterruptedException исключение вынесли в метод
     *
     */

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * завершаем все потоки
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("first"));
        threadPool.work(() -> System.out.println("second"));
        threadPool.work(() -> System.out.println("third"));
        threadPool.shutdown();
    }
}
