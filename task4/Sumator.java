package task4;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Sumator extends RecursiveTask<Long> {
    private int[] array;

    public Sumator(int[] array) {
        this.array = array;
    }

    @Override
    public Long compute() {
        long result = 0L;
        if (array.length < 20) {
            for (int value : array) {
                result += value;
                System.out.println(result + " array length: " + array.length);
            }
            return result;
        } else {
            Sumator firstHalf = new Sumator(Arrays.copyOfRange(array, 0, array.length / 2));
            firstHalf.fork();
            Sumator secondHalf = new Sumator(Arrays.copyOfRange(array, array.length / 2, array.length));
            return secondHalf.compute() + firstHalf.join();
        }
    }
}
