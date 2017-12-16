package task5.view;

public class MainView implements Constants{
    public void showMenu(){
        System.out.println(COUNT);
        System.out.println(SHOW);
        System.out.println(CHANGE);
        System.out.println(EXIT_OPTION);
    }

    public void displayMessage(String message){
        System.out.println(message);
    }
}
