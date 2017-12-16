package task2;

public class Printer implements Runnable {
    private Processor count;

    public Printer(Processor count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000_000; i++) {
            System.out.println(count.getCount());
        }
    }
}