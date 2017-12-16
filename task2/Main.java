package task2;

public class Main {

    public static void main(String[] args) {
        Processor count = new ProcessorSync();
        new Thread(new Printer(count)).start();
        new Thread(new Counter(count)).start();
    }
}
