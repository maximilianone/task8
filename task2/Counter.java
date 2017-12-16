package task2;

public class Counter implements Runnable {
    private Processor count;

    public Counter(Processor count) {
        this.count = count;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000_000; i++) {
            count.increment();
        }
    }
}
