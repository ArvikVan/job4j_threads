package pool;

import java.util.concurrent.RecursiveTask;

/**
 * @author ArvikV
 * @version 1.1
 * @since 25.11.2021
 * 1.1
 *  extends RecursiveTask сделайте параметризованным extends RecursiveTask<Integer>
 *  добавлен еще один тест
 */
public class ParallelMergeSort extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int findMe;

    public ParallelMergeSort(int[] array, int from, int to, int findMe) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.findMe = findMe;
    }

    /**
     *  метод fork() служит для деления. Это аналогично тому,
     *  что мы запустили бы рекурсивный метод еще раз
     *  метод join(). Этот метод как раз дает пулу знать, что нужно запустить задачу в отдельном потоке
     *  метод invoke(). Этот метод служит для запуска выполнения
     * @return на выходе получим индекс объекта который ищем.
     * если массив меньше 10 до получаем индекс сразу
     */
    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = from; i < to; i++) {
                if (array[i] == findMe) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid, findMe);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to, findMe);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }
}
