package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public abstract class Promotions {
    protected String name;
    protected String details;
    protected int priority;

    public Promotions () {
        this.name = "";
        this.details = "";
    }

    public abstract Benefit prepareBenefit(ShoppingItem item);

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
