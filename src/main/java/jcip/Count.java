package jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author ArvikV
 * @version 1.0
 * @since 18.11.2021
 * @ ThreadSafe говорит пользователям данного класса, что класс можно использовать в многопоточном
 * режиме и он будет работать правильно.
 * @ GuardedBy("this") выставляется над общим ресурсом. Аннотация имеет входящий параметр.
 * Он указывает на объект монитора, по которому мы будем синхронизироваться.
 */

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
