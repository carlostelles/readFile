package xyz.carlostelles.readFile.structure;

public class DataOut {
    private String filename;
    private Integer amountClients;
    private Integer amountSalesmen;
    private Long greaterSale;
    private String worstSalesman;

    public DataOut(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getAmountClients() {
        return amountClients;
    }

    public void setAmountClients(Integer amountClients) {
        this.amountClients = amountClients;
    }

    public Integer getAmountSalesmen() {
        return amountSalesmen;
    }

    public void setAmountSalesmen(Integer amountSalesmen) {
        this.amountSalesmen = amountSalesmen;
    }

    public Long getGreaterSale() {
        return greaterSale;
    }

    public void setGreaterSale(Long greaterSale) {
        this.greaterSale = greaterSale;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    public String toString() {
        return "amountClients: " + this.getAmountClients() + "\r\n" +
                "amountSalesman: " + this.getAmountSalesmen() + "\r\n" +
                "idGreaterSale: " + this.getGreaterSale() + "\r\n" +
                "worstSalesman: " + this.getWorstSalesman();
    }
}
