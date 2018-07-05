package xyz.carlostelles.readFile.structure;

import java.util.*;

public class DataIn {
    private String filename;
    private List<Salesman> salesmen;
    private List<Client> clients;
    private List<Sale> sales;

    public DataIn(String filename) {
        this.filename = filename;
        this.salesmen = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.sales = new ArrayList<>();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    public void addSalesmen(Salesman salesman) {
        this.salesmen.add(salesman);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClients(Client client) {
        this.clients.add(client);
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public void addSales(Sale sale) {
        this.sales.add(sale);
    }

    public Salesman findSalesmanByName(String name) {
        return this.getSalesmen().stream()
                .filter(s -> s.getName().contentEquals(name))
                .findAny().get();
    }

    public Sale findGreaterSale() {
        return this.getSales().stream()
                .filter(sale -> sale.getItens().stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .max().isPresent()).findFirst().get();
    }

    public Salesman findWorstSalesman() {
        Map<Salesman, Double> salesmanItemMap = new HashMap<>();

        this.getSales().forEach(sale -> {
            if (Objects.isNull(salesmanItemMap.get(sale.getSalesman()))) {
                salesmanItemMap.put(sale.getSalesman(), 0D);
            }

            Double newValue = salesmanItemMap.get(sale.getSalesman()) + sale.getItens().stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

            salesmanItemMap.put(sale.getSalesman(), newValue);
        });

        return Collections.max(salesmanItemMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }
}
