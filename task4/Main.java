package task4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int THREAD_NUMBER = 8;

    public static void main(String[] args) {
        int[] array = new int[1000_000];
        Random random = new Random();

        for(int i=0; i<1000_000; i++){
            array[i]=random.nextInt(100);
        }

        ForkJoinPool pool = new ForkJoinPool(THREAD_NUMBER);
        System.out.println(pool.invoke(new Sumator(array)));

    }
}
