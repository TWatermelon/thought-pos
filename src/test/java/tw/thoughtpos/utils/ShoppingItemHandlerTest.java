package tw.thoughtpos.utils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.DiscountPromotions;

public class ShoppingItemHandlerTest {
    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "apple";
    private static final double APPLE_PRICE = 5d;
    private static final int APPLE_AMOUNT = 3;
    private static final String KEYRING_BARCODE = "ITEM000003";
    private static final double KEYRING_PRICE = 6d;
    private static final int KEYRING_AMOUNT = 2;

    @Test
    @Ignore
    public void should_bind_the_right_goods_to_shoppingitem() {
        ShoppingItem shoppingItem = new ShoppingItem(APPLE_BARCODE, APPLE_AMOUNT);
        ShoppingItemHandler.bind_goods_to_shoppingitem(shoppingItem);

        assertThat(shoppingItem.getGoods().getName(), is(APPLE_NAME));
        assertThat(shoppingItem.getGoods().getPrice(), is(APPLE_PRICE));
    }


    @Test
    @Ignore
    public void should_bind_the_right_promotions_to_goods() {
        ShoppingItem shoppingItem = create_shoppingitem_with_goods_binded(
                APPLE_BARCODE, APPLE_PRICE, APPLE_AMOUNT);
        ShoppingItemHandler.bind_promotions_to_goods(shoppingItem.getGoods());

        assertThat(shoppingItem.getGoods().getPromotions().calculate(shoppingItem).getAllowance(), is(3d));
    }

    @Test
    @Ignore
    public void should_calculate_all_of_the_right_benefits_for_item_list() {
        List<ShoppingItem> shoppingItemList = create_shoppingitem_list();
        shoppingItemList.get(0).getGoods().setPromotions(new DiscountPromotions(0.8d));
        ShoppingItemHandler.calculate_benefits_for_shoppingitem_list(shoppingItemList);

        assertThat(shoppingItemList.get(0).getBenefit().getAllowance(), is(3d));
        assertThat(shoppingItemList.get(1).getBenefit().getAllowance(), is(0d));
    }

    private List<ShoppingItem> create_shoppingitem_list() {
        ShoppingItem appleItem = create_shoppingitem_with_goods_binded(
                APPLE_BARCODE, APPLE_PRICE, APPLE_AMOUNT);
        ShoppingItem keyringItem = create_shoppingitem_with_goods_binded(
                KEYRING_BARCODE, KEYRING_PRICE, KEYRING_AMOUNT);

        List<ShoppingItem> shoppingItemList = new ArrayList<>();
        shoppingItemList.add(appleItem);
        shoppingItemList.add(keyringItem);
        return shoppingItemList;
    }

    private ShoppingItem create_shoppingitem_with_goods_binded(String barcode, double price, int amount) {
        Goods goods = new Goods(barcode);
        goods.setPrice(price);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setAmount(amount);
        shoppingItem.setGoods(goods);
        return shoppingItem;
    }
}
