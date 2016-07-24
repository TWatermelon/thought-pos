package tw.thoughtpos.promotions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class AmountFreePromotionsTest {
    private static final String BARCODE = "ITEM000001";

    @Test
    public void should_prepare_right_benefit() {
        ShoppingItem item = new ShoppingItem(BARCODE, 10);
        item.setGoods(createGoods());

        Promotions promotions = new AmountFreePromotions("买二赠一", "2 1");
        Benefit benefit = promotions.prepareBenefit(item);

        assertBenefit(benefit, "买二赠一", 3);
        ShoppingItem item1 = new ShoppingItem(BARCODE, 2);
        item.setGoods(createGoods());
        Benefit benefit1 = promotions.prepareBenefit(item1);
        assertBenefit(benefit1, "", 0);


    }

    private void assertBenefit(Benefit benefit, String expectedName, int expectedSaveAmout) {
        assertThat(benefit.getName(), is(expectedName));
        assertThat(benefit.getSaveAmount(), is(expectedSaveAmout));
    }


    private Goods createGoods() {
        Goods goods = new Goods(BARCODE);
        goods.setPrice(20d);
        return goods;
    }
}
