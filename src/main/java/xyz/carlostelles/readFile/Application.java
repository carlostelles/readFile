package xyz.carlostelles.readFile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {

        String userHome = "user.home";
        String path = System.getProperty(userHome);
        String pathIn = path + "/data/in/";
        String pathOut = path + "/data/out/";

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Execute(pathIn, pathOut));
    }

}