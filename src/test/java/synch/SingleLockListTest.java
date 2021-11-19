package synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 */
public class SingleLockListTest {
    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> singleLockList = new SingleLockList(
                new ArrayList());
        Thread first = new Thread(() -> singleLockList.add(1));
        Thread second = new Thread(() -> singleLockList.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        singleLockList.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

}