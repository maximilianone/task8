package task5;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFilter implements Callable<Integer> {
    private ExecutorService pool;
    private File directory;
    private File destination;
    private String word;

    public FileFilter(ExecutorService pool, File directory, File destination, String word){
        this.pool = pool;
        this.directory = directory;
        this.destination = destination;
        this.word=word;
    }

    public int searchWord(File file) throws FileNotFoundException{
        int count = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(file))){
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher("");
            while (scanner.hasNextLine()){
                matcher.reset(scanner.nextLine());
                while (matcher.find()){
                    count++;
                }
            }
        }
        if(count>0){
            writeToFile(file, count);
        }
        return count;
    }

    @Override
    public Integer call() throws FileNotFoundException{
        int total = 0;
        try{
            File[] files = directory.listFiles();
             for (File file: files){
                 if(file.isDirectory()){
                     FileFilter counter = new FileFilter(pool, file, destination, word);
                     Future<Integer> res = pool.submit(counter);
                     total+=res.get();
                 }else{
                     total+=searchWord(file);
                 }
             }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return total;
    }

    private void writeToFile(File file, int count){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(destination, true)))){
            out.println(file.getName()+ ": number of words - " + count);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
