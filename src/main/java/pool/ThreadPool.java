package pool;

import bbq.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ArvikV
 * @version 1.0
 * @since 23.11.2021
 */
public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    /**
     * метод добавляет задачи в блокирующую очередь tasks
     * @param job на входе задача которую надо добавить в блок.очередь
     * @throws InterruptedException исключение вынесли в метод
     * добавляем задачи tasks.offer(job); потоков в кол-ве size согласно условию
     * Проверяем состояние флага Thread.currentThread().isInterrupted()
     * возвращаем объект из внутренней коллекции
     * заполняем жобами блок.очередь
     *
     */
    public void work(Runnable job) throws InterruptedException {
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
}
