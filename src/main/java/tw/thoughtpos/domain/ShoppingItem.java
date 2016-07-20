package tw.thoughtpos.domain;

import tw.thoughtpos.promotions.Benefit;

public class ShoppingItem {
    private String barcode;
    private int amount;
    private Goods goods;
    private Benefit benefit;

    public ShoppingItem() {
    }

    public ShoppingItem(String barcode, int amount) {
        this.barcode = barcode;
        this.setAmount(amount);
        this.goods = new Goods(barcode);
        this.benefit = new Benefit();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getAmount() {
        return amount;
    }

    public double getSubtotal() {
        return getAmount() * goods.getPrice();
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public Benefit getBenefit() {
        return benefit;
    }

}
