package task3;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Map<Integer, Integer> myConcurrentMap = new ConcurrentHashMap<>();
        Map<Integer,Integer> myMap = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i<20;i++){
            int value = random.nextInt(20);
            try {
                Thread t = new Thread(new Writer(myMap, value));
                Thread t1 = new Thread(new Reader(myMap, value));
                t.start();
                t1.start();
                t.join();
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.nanoTime()-start);
    }
}
//74505130
//28953453
