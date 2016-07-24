package tw.thoughtpos.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.thoughtpos.BaseIntegrationTest;
import tw.thoughtpos.repository.GoodsRepository;

public class GoodsControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private GoodsController goodsController;

    @Autowired
    private GoodsRepository goodsRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(goodsController).build();

    }

    @Test
    public void should_find_right_goods_given_barcode() throws Exception {

    }

}
