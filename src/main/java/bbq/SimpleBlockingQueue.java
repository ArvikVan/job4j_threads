package bbq;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ArvikV
 * @version 1.1
 * @since 20.11.2021
 * Реализуйте шаблон Producer Consumer.
 * Для этого вам необходимо реализовать собственную версию bounded blocking queue. Это блокирующая очередь,
 * ограниченная по размеру. В данном шаблоне Producer помещает данные в очередь, а Consumer извлекает данные из очереди.
 * 1.1 убран геттер на поле limit, за ненадобностью.
 */
@ThreadSafe
public final class SimpleBlockingQueue<T> {
    @GuardedBy("this")
      private final Queue<T> queue = new LinkedList<>();
      private final int limit;

 public SimpleBlockingQueue(final int limit) {
  this.limit = limit;
 }
 /**
  * метод должен вернуть заполненный список
  * @param value то что мы должны добавить в коллекцию
  * @throws InterruptedException заменяем трай катч
  * ждем пока не заполнится список, заполняя
  */
 public synchronized void offer(T value) throws InterruptedException {
       while (queue.size() == this.limit) {
        wait();
       }
       queue.add(value);
       notifyAll();
      }
     /**
      * Метод poll() должен вернуть объект из внутренней коллекции.
      * Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания.
      * @return пустой список
      * poll()- Retrieves and removes the head of this queue, or returns null if this queue is empty
      */
      public synchronized T poll() throws InterruptedException {
       while (queue.isEmpty()) {
           wait();
       }
       notifyAll();
       return queue.poll();
      }
}
