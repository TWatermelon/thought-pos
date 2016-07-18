package tw.thoughtpos.promotions;

public class Benefit {

    private double allowance;
    private String name;
    private String details;
    private int saveAmount;

    public double getAllowance() {
        return this.allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSaveAmount() {
        return this.saveAmount;
    }

    public void setSaveAmount(int saveAmount) {
        this.saveAmount = saveAmount;
    }

    public String getName() {
        return name;
    }
}
