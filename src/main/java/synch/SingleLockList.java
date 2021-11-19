package synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 * Чтобы обеспечивать потокобезопасность, нужно каждый метод сделать synchonized.
 * В java уже есть аналогичный метод Collections.synchronizedList().
 * Этот метод делает обертку над коллекциями типа List.
 * Важно. Объект - это ссылка, поэтому нам нужно работать с копией данных.
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    /**
     * конструктор
     * @param list на входе список (работаем с копией данных)
     *             которую берем из копи
     *             можно обернуть в Collections.synchronizedList()
     */
    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    /**
     * метод описывает добавление
     * @param value значение которое добавим
     */
    public synchronized void add(T value) {
        list.add(value);
    }

    /**
     * метод описывает получение
     * @param index индекс на входе, по которому будем получать
     * @return на выходе по индексу получаем
     */
    public synchronized T get(int index) {
        return list.get(index);
    }

    /**
     * метод копи для итератора
     * @param list на входе список который будем копировать
     * @return на выходе список копия
     */
    private List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
