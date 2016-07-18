package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public class DiscountPromotions implements Promotions {
    private double rate;
    private String name;
    private String details;

    public DiscountPromotions(double rate) {
        this.rate = rate;
    }

    public Benefit calculate(ShoppingItem item) {
        Benefit benefit = new Benefit();
        benefit.setAllowance((1 - rate) * item.getSubtotal());
        benefit.setName(name);
        benefit.setDetails(details);
        return benefit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
