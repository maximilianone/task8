package task2;

public class Main {

    public static void main(String[] args) {
        Processor count = new ProcessorSync();
        new Printer(count);
        new Counter(count);
    }
}
