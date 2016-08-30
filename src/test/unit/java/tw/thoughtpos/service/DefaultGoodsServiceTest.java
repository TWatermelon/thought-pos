package tw.thoughtpos.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.repository.GoodsRepository;

@Service
public class DefaultGoodsServiceTest {
    private static final String BARCODE = "ITEM00007";
    @InjectMocks
    private DefaultGoodsService defaultGoodsService;

    @Mock
    private GoodsRepository goodsRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }

    @Test
    public void should_add_right_goods_given_barcode_and_goods() {
        Goods goods = new Goods(BARCODE);
        goods.setName("pen");
        goods.setPrice(30d);
        goods.setUnit("支");
        when(defaultGoodsService.addGoods(goods)).thenReturn(goods);
        assertThat(goodsRepository.save(goods), is(goods));
    }

    @Test
    public void should_find_right_goods_given_barcode() {
        Goods expectedGoods = new Goods(BARCODE);
        when(defaultGoodsService.findGoods(BARCODE)).thenReturn(expectedGoods);
        assertThat(goodsRepository.findOne(BARCODE), is(expectedGoods));
    }
}
