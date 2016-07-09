package tw.thoughtpos.promotions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;


public class DiscountPromotionsTest {

    private static final String BARCODE = "ITEM000001";

    @Test
    public void should_calculate_right_benefit() {
        ShoppingItem item = new ShoppingItem(BARCODE, 10);
        item.setGoods(createGoods());

        Benefit benefit = new DiscountPromotions(0.8d).calculate(item);
        assertEquals(benefit.getAllowance(), 40d, 0.00001);
    }

    private Goods createGoods() {
        Goods goods = new Goods(BARCODE);
        goods.setPrice(20d);
        return goods;
    }
}
