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

        assertThat(benefit.getName(), is("买二赠一"));
        assertThat(benefit.getSaveAmount(), is(3));
    }

    private Goods createGoods() {
        Goods goods = new Goods(BARCODE);
        goods.setPrice(20d);
        return goods;
    }
}
