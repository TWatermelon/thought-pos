package tw.thoughtpos.promotions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.parse.Item;

public class DiscountPromotionsTest {
    @Test
    public void should_calculate_right_benefit() {
        Item item = new Item("ITEM000001", 10);
        Benefit benefit;
        item.setTotalPrice(100d);
        benefit = new DiscountPromotions(0.8d).calculate(item);
        assertThat(benefit.getAllowance(), is(80d));
        item.setTotalPrice(100d);
        benefit = new DiscountPromotions(0.75d).calculate(item);
        assertThat(benefit.getAllowance(), is(75d));
    }
}
