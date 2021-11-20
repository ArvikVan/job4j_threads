package buffer;

import bbq.SimpleBlockingQueue;

/**
 * @author ArvikV
 * @version 1.0
 * @since 20.11.2021
 * Проверяем состояние флага Thread.currentThread().isInterrupted()
 * Метод join() позволяет вызывающему потоку ждать поток, у которого этот метод вызывается.
 */
public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            /**
                             * В блоке catch нужно дополнительно вызывать прерывание нити
                             * для того чтобы прерывания действительно произошло.
                             */
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        final Thread prod = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        prod.start();
        prod.join();
        /**
         * выставляем флаг прерывания. Это рекомендации о том, чтобы нить завершала свою работу.
         */
        consumer.interrupt();
    }
}
