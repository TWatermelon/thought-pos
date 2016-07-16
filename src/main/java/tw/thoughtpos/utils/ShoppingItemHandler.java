package tw.thoughtpos.utils;

import java.util.List;

import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Benefit;
import tw.thoughtpos.repository.GoodsRepository;

public class ShoppingItemHandler {
    public static void bind_goods_to_shoppingitem(ShoppingItem shoppingItem) {
        shoppingItem.setGoods(GoodsRepository
                .findGoods(shoppingItem.getBarcode()));
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
