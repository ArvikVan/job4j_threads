package cas;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.11.2021
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    /**
     * expectedCount - переменная которой присвоим текущее значение сount
     * newCount - переменная которой присовим инкремент count
     * будем это делать до тех пор пока !(count и expectedCount равны)
     *
     */
    public void increment() {
        Integer expectedCount;
        int newCount;
        do {
            expectedCount  = count.get();
            newCount = expectedCount + 1;
        } while (!count.compareAndSet(expectedCount, newCount));
    }

    /**
     * метод описывает получение значения
     * @return на выходе текущее значение count
     */
    public int getValue() {
        return count.get();
    }

}
