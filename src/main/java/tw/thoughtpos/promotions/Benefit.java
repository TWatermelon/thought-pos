package tw.thoughtpos.promotions;

public class Benefit {

    private String name;
    private double allowance;
    private int saveAmount;

    public Benefit() {
        this.name = "";
        this.allowance = 0d;
        this.saveAmount = 0;
    }

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
