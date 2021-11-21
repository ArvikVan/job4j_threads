package cas;

import linked.Node;
import net.jcip.annotations.NotThreadSafe;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.11.2021
 * Не потокобезпасен
 */
@NotThreadSafe
public class Stack<T> {
    private Node<T> head;

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        if (head == null) {
            head = temp;
            return;
        }
        temp.next = head;
        head = temp;
    }

    public T poll() {
        Node<T> temp = head;
        if (temp == null) {
            throw new IllegalStateException("Stack is empty");
        }
        head = temp.next;
        temp.next = null;
        return temp.value;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
