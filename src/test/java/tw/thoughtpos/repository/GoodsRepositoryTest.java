package tw.thoughtpos.repository;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.domain.Goods;

public class GoodsRepositoryTest {

    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "苹果";
    private static final double APPLE_PRICE = 5d;
    private static final String APPLE_PROMOTIONS = "DiscountPromotions";

    private GoodsRepository goodsRepository;

    @Before
    public void setUp() {
        goodsRepository = new DefaultGoodsRepository();
    }

    @Test
    public void should_get_the_right_goods_given_barcode() {
        Goods goods = goodsRepository.findGoods(APPLE_BARCODE);
        assertThat(goods.getName(), is(APPLE_NAME));
        assertThat(goods.getPrice(), is(APPLE_PRICE));
        assertNotEquals(goods.getPromotions(), null);
    }
}
