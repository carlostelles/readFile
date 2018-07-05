package xyz.carlostelles.readFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import xyz.carlostelles.readFile.structure.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileIn {
    private List<String> readLine(String line) {
        if (StringUtils.isNotEmpty(line)) {
            return Arrays.asList(line.split("\u00e7"));
        }
        return null;
    }

    public DataIn readFile(Path path) throws IOException {
        if (!FilenameUtils.getExtension(path.toString()).equals("dat")) {
            return null;
        }

        File f = path.toFile();
        List<String> lines = FileUtils.readLines(f, "ISO-8859-15");
        DataIn dataIn = new DataIn(FilenameUtils.getBaseName(path.toString()));

        lines.forEach(s -> {
            List<String> lineValues = readLine(s);

            assert Objects.nonNull(lineValues);
            switch (lineValues.get(0)) {
                case "001":
                    Salesman salesman = new Salesman();
                    salesman.setCpf(lineValues.get(1));
                    salesman.setName(lineValues.get(2));
                    salesman.setSalary(Double.valueOf(lineValues.get(3)));
                    dataIn.addSalesmen(salesman);
                    break;
                case "002":
                    Client client = new Client();
                    client.setCnpj(lineValues.get(1));
                    client.setName(lineValues.get(2));
                    client.setBusinessArea(lineValues.get(3));
                    dataIn.addClients(client);
                    break;
                case "003":
                    Sale sale = new Sale();
                    sale.setId(Long.valueOf(lineValues.get(1)));
                    Salesman findSalesman = dataIn.findSalesmanByName(lineValues.get(3));
                    sale.setSalesman(findSalesman);
                    List<Item> items = new ArrayList<>();
                    List<String> lineList = Arrays.asList(lineValues.get(2)
                            .replace("[", "")
                            .replace("]", "")
                            .split(","));
                    lineList.forEach(lineItens -> {
                        List<String> itemList = Arrays.asList(lineItens.split("-"));
                        Item item = new Item();
                        item.setId(Long.valueOf(itemList.get(0)));
                        item.setQuantity(Long.valueOf(itemList.get(1)));
                        item.setPrice(Double.valueOf(itemList.get(2)));
                    });
                    sale.setItens(items);
                    dataIn.addSales(sale);
                    break;
            }
        });

        return dataIn;
    }
}
