package tw.thoughtpos.register;

import static tw.thoughtpos.utils.FormatUtil.format;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class FullMinusRecord extends Record {
    @Override
    public String showRecord() {
        Goods goods = getShoppingItem().getGoods();
        return new StringBuilder().append("名称：")
                .append(goods.getName()).append(",")
                .append("价格：").append(getSubtotal()).append("（元）").toString();

    }

    private String getSubtotal() {
        ShoppingItem shoppingItem = getShoppingItem();
        Goods goods = shoppingItem.getGoods();
        return format(goods.getPrice() * shoppingItem.getAmount());
    }

    public void setShoppingItem(ShoppingItem shoppingItem) {
        this.shoppingItem = shoppingItem;
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
}
