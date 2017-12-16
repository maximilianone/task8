package task2;

public class ProcessorSync extends Processor {
    private boolean incremented = true;


    @Override
    public synchronized void increment() {
        while (incremented) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        incremented = true;
        notify();
    }

    @Override
    public synchronized int getCount() {
        while (!incremented) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        incremented = false;
        notify();
        return count;
    }
}
