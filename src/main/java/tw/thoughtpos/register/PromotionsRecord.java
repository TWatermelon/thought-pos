package tw.thoughtpos.register;

import tw.thoughtpos.domain.ShoppingItem;

public abstract class PromotionsRecord {
    protected ShoppingItem shoppingItem;
    public abstract  String showRecord();
}
