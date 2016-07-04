package tw.pos.parse;

public class Item {
    private String barcode;
    private int amount;

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
}
