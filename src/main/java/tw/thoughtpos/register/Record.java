package tw.thoughtpos.register;

import tw.thoughtpos.domain.ShoppingItem;

public abstract class Record {
    protected ShoppingItem shoppingItem;
    public abstract  String showRecord();
}
