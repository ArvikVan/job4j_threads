/**
 * @author arvikv
 * @version 1.0
 * @since 16.11.2021
 * Если поле класса обозначено volatile, то чтение и запись переменной будет
 * происходить только из RAM памяти процессора.
 */

public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {

    }
}
