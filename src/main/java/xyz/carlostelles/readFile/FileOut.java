package xyz.carlostelles.readFile;

import xyz.carlostelles.readFile.structure.DataIn;
import xyz.carlostelles.readFile.structure.DataOut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOut {
    public FileOut(String pathOut, List<DataIn> dataIn) {
        dataIn.forEach(in -> {
            DataOut dataOut = new DataOut(in.getFilename());
            dataOut.setAmountClients(in.getClients().size());
            dataOut.setAmountSalesmen(in.getSalesmen().size());
            //dataOut.setGreaterSale(in.findGreaterSale().getId());
            dataOut.setWorstSalesman(in.findWorstSalesman().getName());
            try {
                File file = new File(pathOut + dataOut.getFilename() + ".done.dat");
                file.getParentFile().mkdirs();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(dataOut.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
