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
public class ParallelMergeSortTest {
    @Test
    public void whenArraylessThen10() {
        int[] array = {11, 12, 13, 14, 15, 16, 17};
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(
                array, 0, array.length, 16);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int index = (int) forkJoinPool.invoke(parallelMergeSort);
        assertThat(5, is(index));
    }

    @Test
    public void whenArrayMoreThen10() {
        int[] array = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(
                array, 0, array.length, 19
        );
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int index = (int) forkJoinPool.invoke(parallelMergeSort);
        assertThat(8, is(index));
    }

}