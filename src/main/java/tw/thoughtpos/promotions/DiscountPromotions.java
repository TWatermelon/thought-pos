package tw.thoughtpos.promotions;

import tw.thoughtpos.parse.Item;

public class DiscountPromotions implements Promotions {
    private double rate;

    public DiscountPromotions(double rate) {
        this.rate = rate;
    }

    public Benefit calculate(Item item) {
        Benefit benefit = new Benefit();
        benefit.setAllowance(rate * item.getTotalPrice());
        return benefit;
    }
}
