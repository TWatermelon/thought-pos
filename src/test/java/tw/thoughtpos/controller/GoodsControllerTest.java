package tw.thoughtpos.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.CREATED;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.service.DefaultGoodsService;

public class GoodsControllerTest {
    private static final String BARCODE = "ITEM00007";
    @InjectMocks
    private GoodsController goodsController;

    @Mock
    private DefaultGoodsService defaultGoodsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }

    @Test
    public void should_add_right_goods() {
        Goods goods = new Goods(BARCODE);
        when(defaultGoodsService.addGoods(BARCODE, goods)).thenReturn(goods);
        ResponseEntity<?> responseEntity = goodsController.addGoods(BARCODE, goods);
        assertThat(responseEntity.getStatusCode(), is(CREATED));
        assertThat(responseEntity.getBody(), is(goods));

    }



}
