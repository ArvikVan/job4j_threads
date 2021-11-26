package pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author ArvikV
 * @version 1.3
 * @since 25.11.2021
 * 1.1
 *  extends RecursiveTask сделайте параметризованным extends RecursiveTask<Integer>
 *  добавлен еще один тест
 *  1.2 ваша текущая реализация в определенных случаях получает значение индекса -1, чего быть не должно
 *  1.3 в цикле i < to;
 *  rightSort = new ParallelMergeSort(array, mid + 1, to - 1, findMe); так (array, mid, to, findMe)
 *  1.4
 *  3. Переименуйте класс, а то складывается впечатление что он для сортировки предназначен
 *  1. чтобы 2 раза с одного и того же не начинать Все же тут добавьте +1
 *  ParallelFind rightSort = new ParallelFind(array, mid + 1, to, findMe);
 *  2. Добавьте в класс статический метод, который будет принимать все данные для поиска
 *  и в этом методе будете вызывать invoke, а не в тестах
 *
 */
public class ParallelFind extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int findMe;

    public ParallelFind(int[] array, int from, int to, int findMe) {
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
            for (int i = from; i <= to; i++) {
                if (array[i] == findMe) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelFind leftSort = new ParallelFind(array, from, mid, findMe);
        ParallelFind rightSort = new ParallelFind(array, mid + 1, to - 1, findMe);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    /**
     * Метод в котором запускаем выполнение
     * @param array массив на входе
     * @param findMe значение которое надо найти
     * @return на выходе индекс найденого
     */
    public static Integer search(int[] array, int findMe) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFind(array, 0, array.length - 1, findMe));
    }
}
