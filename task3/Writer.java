package task3;

import java.util.Map;

public class Writer implements Runnable{
    Map myMap;
    int numbers;

    public Writer(Map myMap, int numbers){
        this.myMap=myMap;
        this.numbers=numbers;
    }

    @Override
    public synchronized void run(){
        for(int i = numbers; i<(numbers+10);i++){
            myMap.put(i,i);
        }
    }
}
