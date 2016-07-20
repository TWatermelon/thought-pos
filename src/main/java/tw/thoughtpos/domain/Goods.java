package tw.thoughtpos.domain;

public class Goods {
    private double price;
    private String name;
    private String unit;

    public Goods() {

    }

    public Goods(String barcode) {
        this.price = 0d;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

}
