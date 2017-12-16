package task3;

import java.util.Map;

public class Reader implements Runnable{
    Map myMap;
    int count;

    public Reader(Map myMap, int count){
        this.myMap = myMap;
        this.count = count;
    }
    @Override
    public synchronized void run(){
        for (int i=count;i<count+10;i++) {
            System.out.println(myMap.get(i));
        }
    }

}
