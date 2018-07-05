package xyz.carlostelles.readFile;

import xyz.carlostelles.readFile.structure.DataIn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Execute implements Runnable {
    private String pathIn;
    private String pathOut;

    public Execute(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
    }

    @Override
    public void run() {
        FileIn fileIn = new FileIn();
        List<DataIn> dataIn = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(this.pathIn))) {
            paths.filter(Files::isRegularFile)
                    .forEach(pathFile -> {
                        try {
                            dataIn.add(fileIn.readFile(pathFile));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        new FileOut(this.pathOut, dataIn);
    }
}
