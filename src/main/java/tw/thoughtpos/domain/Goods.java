package tw.thoughtpos.domain;

import tw.thoughtpos.promotions.Promotions;

public class Goods {
    private double price;
    private String name;
    private String unit;
    private Promotions promotions;

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

    public Promotions getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotions promotions) {
        this.promotions = promotions;
    }
}
