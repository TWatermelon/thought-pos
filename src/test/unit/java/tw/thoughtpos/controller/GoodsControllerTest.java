package tw.thoughtpos.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.service.GoodsService;

public class GoodsControllerTest {
    private static final String BARCODE = "ITEM00007";
    @InjectMocks
    private GoodsController goodsController;

    @Mock
    private GoodsService defaultGoodsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }

    @Test
    public void should_add_right_goods() {
        Goods goods = new Goods(BARCODE);
        when(defaultGoodsService.addGoods(goods)).thenReturn(goods);
        ResponseEntity<?> responseEntity = goodsController.addGoods(goods);
        assertThat(responseEntity.getStatusCode(), is(CREATED));
        assertThat(responseEntity.getBody(), is(goods));
    }

    @Test
    public void should_find_right_goods_given_barcode() {
        Goods expectedGoods = new Goods(BARCODE);
        when(defaultGoodsService.findGoods(BARCODE)).thenReturn(expectedGoods);
        ResponseEntity<?> responseEntity = goodsController.findGoods(BARCODE);
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(responseEntity.getBody(), is(expectedGoods));
    }
}
