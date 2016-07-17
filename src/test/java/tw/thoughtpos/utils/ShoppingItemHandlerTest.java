package tw.thoughtpos.utils;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.DiscountPromotions;

public class ShoppingItemHandlerTest {
    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "苹果";
    private static final double APPLE_PRICE = 5d;
    private static final int APPLE_AMOUNT = 3;
    private static final String KEYRING_BARCODE = "ITEM000003";
    private static final double KEYRING_PRICE = 6d;
    private static final int KEYRING_AMOUNT = 2;

    @Test
    public void should_bind_the_right_goods_to_shopping_item() {
        ShoppingItem shoppingItem = new ShoppingItem(APPLE_BARCODE, APPLE_AMOUNT);
        ShoppingItemHandler.bindGoods(shoppingItem);

        assertThat(shoppingItem.getGoods().getName(), is(APPLE_NAME));
        assertThat(shoppingItem.getGoods().getPrice(), is(APPLE_PRICE));
    }

    @Test
    public void should_calculate_all_of_the_right_benefits_for_shopping_item_list() {
        List<ShoppingItem> shoppingItemList = createShoppingItemList();
        shoppingItemList.get(0).getGoods().setPromotions(new DiscountPromotions(0.8d));
        ShoppingItemHandler.calculateBenefits(shoppingItemList);

        assertAllowance(shoppingItemList.get(0), 3d);
        assertAllowance(shoppingItemList.get(1), 0d);
    }

    private void assertAllowance(ShoppingItem item, double expected) {
        assertEquals(expected, item.getAllowance(), 0.00001);
    }

    private List<ShoppingItem> createShoppingItemList() {
        return asList(createShoppingItem(APPLE_BARCODE, APPLE_PRICE, APPLE_AMOUNT),
                createShoppingItem(KEYRING_BARCODE, KEYRING_PRICE, KEYRING_AMOUNT));
    }

    private ShoppingItem createShoppingItem(String barcode, double price, int amount) {
        Goods goods = new Goods(barcode);
        goods.setPrice(price);
        ShoppingItem shoppingItem = new ShoppingItem(barcode, amount);
        shoppingItem.setGoods(goods);
        return shoppingItem;
    }
}
