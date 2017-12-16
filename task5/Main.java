package task5;

import task5.controller.Controller;
import task5.input.MyScanner;
import task5.view.MainView;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        MyScanner scanner = new MyScanner();
        Controller controller = new Controller(view, scanner);
        controller.run();
    }
}
