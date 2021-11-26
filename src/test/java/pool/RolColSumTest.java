package pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static pool.RolColSum.getTask;
import static pool.RolColSum.sum;

/**
 * @author ArvikV
 * @version 1.0
 * @since 27.11.2021
 */
public class RolColSumTest {
    @Test
    public void rowSums() {
        int[][] array = {{1, 11}, {10, 5}};
        RolColSum.Sums[] sums = sum(array);
        assertThat(sums[0].getRowSum(), is(12));
    }

    @Test
    public void colSums() {
        int[][] array = {{5, 8}, {3, 10}};
        RolColSum.Sums[] sums = sum(array);
        assertThat(sums[1].getColSum(), is(18));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] rsl = RolColSum.asyncSum(array);
        Assert.assertThat(rsl[0].getRowSum(), is(6));
        Assert.assertThat(rsl[0].getColSum(), is(12));
    }


}