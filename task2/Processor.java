package task2;

abstract class Processor {
    int count;

    public abstract void increment();

    public abstract int getCount();


    @Override
    public String toString() {
        return "" + count;
    }
}
