package tw.thoughtpos.domain;

public class Goods {
    private double price;

    public Goods(String barcode) {
        this.price = 0d;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
