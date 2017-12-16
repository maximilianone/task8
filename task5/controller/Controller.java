package task5.controller;

import task5.FileFilter;
import task5.input.MyScanner;
import task5.view.MainView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {
    private MainView mainView;
    private MyScanner scanner;
    private File destination= new File("result");

    ExecutorService pool = Executors.newCachedThreadPool();
    private boolean isRunning = true;

    public enum Menu {COUNT, SHOW, CHANGE,  EXIT, DEFAULT}


    public Controller(MainView mainView, MyScanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
    }

    public void run() {
        while (isRunning) {
            mainView.showMenu();
            processOption(scanner.readInput());
        }
    }

    private void processOption(String statement) {
        Menu option;
        try {
            option = Menu.valueOf(statement.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            option = Menu.DEFAULT;
        }
        switch (option) {
            case COUNT:
                countWordAppearances();
                break;
            case SHOW:
                displayResult();
                break;
            case CHANGE:
                changeDestination();
                break;
            case EXIT:
                exit();
                break;
            case DEFAULT:
                mainView.displayMessage(mainView.WRONG_INPUT);
                break;
        }
    }

    private void countWordAppearances() {
        try {
            new PrintWriter(destination).write("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mainView.displayMessage(MainView.REQUEST_DIRECTORY);
        String directory = scanner.readInput();
        mainView.displayMessage(MainView.REQUEST_WORD);
        String word = scanner.readInput();
        FileFilter filter = new FileFilter(pool, new File(directory), destination, word);
        Future<Integer> result = pool.submit(filter);
        try {
            mainView.displayMessage(MainView.RESULT + result.get());
        } catch (InterruptedException | ExecutionException e) {
            mainView.displayMessage(MainView.WRONG_INPUT);
        }
    }

    private void displayResult() {
        try (Scanner scanner = new Scanner(new FileInputStream(destination))) {
            while (scanner.hasNextLine()) {
                mainView.displayMessage(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void changeDestination(){
        mainView.displayMessage(MainView.REQUEST_FILE);
        try{
            destination= new File(scanner.readInput());
        }catch (NullPointerException e){
            mainView.displayMessage(MainView.WRONG_INPUT);
        }
    }


    private void exit() {
        isRunning = false;
        pool.shutdown();
    }
}
