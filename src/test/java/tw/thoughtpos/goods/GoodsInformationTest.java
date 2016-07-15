package tw.thoughtpos.goods;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;

public class GoodsInformationTest {

    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "apple";
    private static final double APPLE_PRICE = 5d;

    @Test
    public void should_get_the_right_goods_given_barcode() {
        Goods goods = GoodsInformation.get_goods_given_barcode(APPLE_BARCODE);
        assertThat(goods.getName(), is(APPLE_NAME));
        assertThat(goods.getPrice(), is(APPLE_PRICE));
        assertNotEquals(goods.getPromotions(), null);
    }
}
