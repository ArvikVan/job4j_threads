package bbq;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 20.11.2021
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
}