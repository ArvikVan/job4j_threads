package cas;

import linked.Node;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.11.2021
 */
@ThreadSafe
public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            temp.next = ref;
        } while (!head.compareAndSet(ref, temp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Stack i,s empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    public static final class Node<T> {
        final T value;
        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
