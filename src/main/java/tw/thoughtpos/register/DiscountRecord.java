package tw.thoughtpos.register;

import tw.thoughtpos.domain.ShoppingItem;

public class DiscountRecord extends PromotionsRecord {
    private String discountType;

    public DiscountRecord(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String showRecord() {
        return new StringBuilder().append("名称：")
                .append(getShoppingItem().getGoods().getName()).append(",")
                .append("折扣：").append(getDiscountType()).toString();
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public void setShoppingItem(ShoppingItem shoppingItem) {
        this.shoppingItem = shoppingItem;
    }

    public String getDiscountType() {
        return discountType;
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
}
