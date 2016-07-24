package tw.thoughtpos.repository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.domain.Goods;

public class GoodsRepositoryTest {

    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "苹果";
    private static final double APPLE_PRICE = 5d;

    private DefaultGoodsRepository goodsRepository;

    @Before
    public void setUp() {
        goodsRepository = new DefaultGoodsRepository();
    }

    @Test
    public void should_get_the_right_goods_given_barcode() {
        Goods apple = new Goods(APPLE_BARCODE);
        apple.setName(APPLE_NAME);
        goodsRepository.getGoodsMap().put(APPLE_BARCODE, apple);
        Goods goods = goodsRepository.findGoods(APPLE_BARCODE);
        assertThat(goods, is(apple));
    }

    @Test
    public void should_add_the_right_goods_given_barcode_and_goods() {
        Goods apple = new Goods(APPLE_BARCODE);
        apple.setUnit("斤");
        apple.setName("apple");
        apple.setPrice(6d);
        goodsRepository.addGoods(APPLE_BARCODE, apple);
        Goods goodsActual = goodsRepository.getGoodsMap().get(APPLE_BARCODE);
        assertThat(goodsActual, is(apple));
    }
}
