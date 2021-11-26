package pool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 25.11.2021
 */
public class ParallelFindTest {
    @Test
    public void whenArraylessThen10() {
        int[] array = {11, 12, 13, 14, 15, 16, 17};
        ParallelFind parallelMergeSort = new ParallelFind(
                array, 0, array.length, 16);
        assertThat(5, is(ParallelFind.search(array, 16)));
    }

    @Test
    public void whenArrayMoreThen10() {
        int[] array = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        ParallelFind parallelMergeSort = new ParallelFind(
                array, 0, array.length, 19
        );
        assertThat(8, is(ParallelFind.search(array, 19)));
    }

    @Test
    public void whenReturnMinusOne() {
        int[] array = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        ParallelFind parallelMergeSort = new ParallelFind(
                array, 0, array.length, 17
        );
        assertThat(6, is(ParallelFind.search(array, 17)));
    }

}