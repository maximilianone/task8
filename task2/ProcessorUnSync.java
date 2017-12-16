package task2;

public class ProcessorUnSync extends Processor {

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
