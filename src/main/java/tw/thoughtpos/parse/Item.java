package tw.thoughtpos.parse;

public class Item {
    private String barcode;
    private int amount;
    private double totalPrice;

    public Item(String barcode, int amount) {
        this.barcode = barcode;
        this.amount = amount;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
