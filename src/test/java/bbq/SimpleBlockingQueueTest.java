package bbq;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author ArvikV
 * @version 1.1
 * @since 20.11.2021
 * whenFetchAllThenGetIt
 * Проверяем состояние флага Thread.currentThread().isInterrupted()
 * Метод join() позволяет вызывающему потоку ждать поток, у которого этот метод вызывается.
 */
public class SimpleBlockingQueueTest {
    @Test
    public void check() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue(5);
        Thread producer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 6; i++) {
                        try {
                            System.out.println("Producer run");
                            simpleBlockingQueue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "PRODUCER"
        );
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 6; i++) {
                        try {
                            System.out.println("Consumer run");
                            simpleBlockingQueue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "CONSUMER"
        );
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }
    /**
     * while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
     * Здесь мы проверяем, что очередь пустая или нить выключили.
     * В блоке catch нужно дополнительно вызывать прерывание нити
     * для того чтобы прерывания действительно произошло.
     * consumer.interrupt();
     * выставляем флаг прерывания. Это рекомендации о том, чтобы нить завершала свою работу.
     */
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}