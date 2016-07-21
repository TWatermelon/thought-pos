package tw.thoughtpos.domain;

public class Goods {
    private double price;
    private String name;
    private String unit;
    private String barcode;

    public Goods() {

    }

    public Goods(String barcode) {
        this.barcode = barcode;
        this.price = 0d;
    }

    public static Goods generateGoods(String barcode, String name, double price, String unit) {
        Goods goods = new Goods(barcode);
        goods.setName(name);
        goods.setPrice(price);
        goods.setUnit(unit);
        return goods;
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

    public String getBarcode() {
        return barcode;
    }
}
