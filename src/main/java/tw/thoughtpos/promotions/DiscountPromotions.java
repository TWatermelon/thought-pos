package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public class DiscountPromotions implements Promotions {
    private double rate;
    private String name;

    public DiscountPromotions(String name, String arguments) {
        this.name = name;
        this.rate = Double.valueOf(arguments);
    }

    public Benefit prepareBenefit(ShoppingItem item) {
        Benefit benefit = new Benefit();
        benefit.setAllowance((1 - rate) * item.getSubtotal());
        benefit.setName(name);
        return benefit;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
