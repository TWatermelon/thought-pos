package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public class AmountFreePromotions extends Promotions {
    private int buyAmount;
    private int freeAmount;

    public AmountFreePromotions(String name, String arguments) {
        super.name = name;
        this.buyAmount = Integer.parseInt(arguments.split(" ")[0]);
        this.freeAmount = Integer.parseInt(arguments.split(" ")[1]);
    }

    @Override
    public Benefit prepareBenefit(ShoppingItem item) {
        Benefit benefit = new Benefit();
        int saveAmount = item.getAmount() / (buyAmount + freeAmount) * freeAmount;
        benefit.setName(saveAmount > 0 ? this.name : "");
        benefit.setSaveAmount(saveAmount);
        return benefit;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public int getFreeAmount() {
        return freeAmount;
    }
}
