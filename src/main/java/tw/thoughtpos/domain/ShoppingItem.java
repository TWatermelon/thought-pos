package tw.thoughtpos.domain;

import static tw.thoughtpos.utils.ConstantUtil.COMMA;
import static tw.thoughtpos.utils.ConstantUtil.MONEY_UNIT;
import static tw.thoughtpos.utils.ConstantUtil.NEW_LINE_CHAR;
import static tw.thoughtpos.utils.FormatUtil.format;

import tw.thoughtpos.promotions.Benefit;

public class ShoppingItem {
    private String barcode;
    private int amount;
    private Goods goods;
    private Benefit benefit;

    public ShoppingItem(String barcode, int amount) {
        this.barcode = barcode;
        this.setAmount(amount);
        this.goods = new Goods(barcode);
    }

    public ShoppingItem() { }

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

    public Goods getGoods() {
        return goods;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public String getAmountInfo() {
        return new StringBuilder().append("数量：").append(this.getAmount()).toString();
    }

    public String getUnitInfo() {
        return new StringBuilder()
                .append(this.getGoods().getUnit())
                .append(COMMA).toString();
    }

    public String getPriceInfo() {
        return new StringBuilder().append("单价：")
                .append(format(this.getGoods().getPrice()))
                .append(MONEY_UNIT)
                .append(COMMA).toString();
    }

    public String getNameInfo() {

        return new StringBuilder().append("名称：")
                .append(this.getGoods().getName())
                .append(COMMA).toString();
    }

    public String getAllowanceInfo() {
        StringBuilder builder = new StringBuilder();
        double allowance = getAllowance();
        return allowance > 0 ? builder.append(COMMA + "优惠")
                .append(format(allowance)).append(MONEY_UNIT).toString() : builder.toString();
    }

    public double getAllowance() {
        return getBenefit().getAllowance();
    }

    public String getSubtotalInfo() {
        return new StringBuilder().append("小计:")
                .append(format(getSubtotalPrice()))
                .append(MONEY_UNIT).toString();
    }

    public double getSubtotalPrice() {
        return getSubtotal() - getAllowance();
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getNameInfo()).append(getAmountInfo())
                .append(getUnitInfo()).append(getPriceInfo()).append(getSubtotalInfo())
                .append(getAllowanceInfo())
                .append(NEW_LINE_CHAR).toString();
    }
}
