package tw.thoughtpos.utils;

import java.util.List;

import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.repository.GoodsRepository;

public class ShoppingItemHandler {
    public static void bindGoods(ShoppingItem shoppingItem) {
        shoppingItem.setGoods(GoodsRepository
                .findGoods(shoppingItem.getBarcode()));
    }

    public static void calculateBenefits(List<ShoppingItem> shoppingItems) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (shoppingItem.getGoods().getPromotions() != null) {
                shoppingItem.setBenefit(
                        shoppingItem.getGoods().getPromotions().calculate(shoppingItem));
            }
        }
    }
}
