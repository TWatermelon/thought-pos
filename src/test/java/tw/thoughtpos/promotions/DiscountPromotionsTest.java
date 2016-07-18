package tw.thoughtpos.promotions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;


public class DiscountPromotionsTest {

    private static final String BARCODE = "ITEM000001";

    @Test
    public void should_calculate_right_benefit() {
        ShoppingItem item = new ShoppingItem(BARCODE, 10);
        item.setGoods(createGoods());

        DiscountPromotions promotions = new DiscountPromotions("单品打折", "0.8d");
        Benefit benefit = promotions.prepareBenefit(item);

        assertEquals(benefit.getAllowance(), 40d, 0.00001);
        assertThat(benefit.getName(), is("单品打折"));
    }

    private Goods createGoods() {
        Goods goods = new Goods(BARCODE);
        goods.setPrice(20d);
        return goods;
    }
}
