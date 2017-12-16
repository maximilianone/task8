package task1;

public class MainLambda {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 10; i > 0; i--) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Bomb");
        });
        thread.start();
    }
}
