package tw.thoughtpos.domain;

public class ShoppingItem {
    private String barcode;
    private int amount;
    private Goods goods;

    public ShoppingItem(String barcode, int amount) {
        this.barcode = barcode;
        this.amount = amount;
        this.goods = new Goods(barcode);
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
        return getAmount() * getPrice();
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public double getPrice() {
        return goods.getPrice();
    }
}
