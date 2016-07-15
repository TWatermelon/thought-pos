package tw.thoughtpos.utils;

import java.util.List;

import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Benefit;
import tw.thoughtpos.tw.thoughtpos.goods.GoodsInformation;

public class ShoppingItemHandler {
    public static void bind_goods_to_shoppingitem(ShoppingItem shoppingItem) {
        shoppingItem.setGoods(GoodsInformation
                .get_goods_given_barcode(shoppingItem.getBarcode()));
    }

    public static void calculate_benefits_for_shoppingitem_list(
            List<ShoppingItem> shoppingItemList) {
        for (ShoppingItem shoppingItem : shoppingItemList) {
            if (shoppingItem.getGoods().getPromotions() != null) {
                shoppingItem.setBenefit(
                        shoppingItem.getGoods().getPromotions().calculate(shoppingItem));
            } else {
                shoppingItem.setBenefit(new Benefit());
            }
        }
    }
}
