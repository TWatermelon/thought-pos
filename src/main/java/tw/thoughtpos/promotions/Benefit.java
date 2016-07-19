package tw.thoughtpos.promotions;

import tw.thoughtpos.utils.FormatUtil;

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
        this.allowance = Double.parseDouble(FormatUtil.format(allowance));
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
