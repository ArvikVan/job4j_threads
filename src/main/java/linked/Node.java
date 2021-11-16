package linked;

/**
 * @author arvikv
 * @version 1.1
 * @since 16.11.2021
 * Правила создания Immutable объекта.
 * 1. Все поля отмечены final.
 * 2. Состояние объекта не изменяется после создания объекта.
 * Поля отметил final
 * убрал сеттеры позволяющие менять состояние объекта
 * 1,1 immutable это еще и запрет наследования, используйте final на уровне класса
 */
public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
