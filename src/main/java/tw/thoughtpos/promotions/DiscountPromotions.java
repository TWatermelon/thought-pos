package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public class DiscountPromotions extends Promotions {
    private double rate;

    public DiscountPromotions(String name, String arguments) {
        super.name = name;
        this.rate = Double.valueOf(arguments);
    }

    @Override
    public Benefit prepareBenefit(ShoppingItem item) {
        Benefit benefit = new Benefit();
        benefit.setAllowance((1 - rate) * item.getSubtotal());
        benefit.setName(name);
        return benefit;
    }

    public double getRate() {
        return rate;
    }
}
