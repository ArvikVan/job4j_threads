package cas;

import org.junit.Test;
import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.11.2021
 */
public class CASCountTest {
    @Test
    public void whenTwoThreads() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> IntStream.range(0, 150).forEach(x -> count.increment()));
        Thread second = new Thread(
                () -> IntStream.range(0, 150).forEach(x -> count.increment()));
        first.start();
        second.start();
        first.join(2000);
        second.join();
        assertThat(count.getValue(), is(300));

    }

    @Test
    public void whenThreeThreads() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> IntStream.range(0, 100).forEach(a -> count.increment()));
        Thread second = new Thread(
                () -> IntStream.range(0, 100).forEach(a -> count.increment()));
        Thread third = new Thread(
                () -> IntStream.range(0, 100).forEach(a -> count.increment()));
        first.start();
        second.start();
        third.start();
        first.join(1000);
        second.join();
        third.join();
        assertThat(count.getValue(), is(300));
    }
}