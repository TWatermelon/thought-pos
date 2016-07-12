package tw.thoughtpos.register;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class WholesaleRecord extends Record {

    public void setShoppingItem(ShoppingItem shoppingItem) {
        this.shoppingItem = shoppingItem;
    }

    @Override
    public String showRecord() {
        ShoppingItem shoppingItem = getShoppingItem();
        Goods goods = shoppingItem.getGoods();
        return new StringBuilder().append("名称：").append(goods.getName()).append(",")
                .append("数量：").append(shoppingItem.getAmount())
                .append(goods.getUnit()).toString();
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
}
