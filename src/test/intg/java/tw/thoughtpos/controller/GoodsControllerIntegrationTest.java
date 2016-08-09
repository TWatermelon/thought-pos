package tw.thoughtpos.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.thoughtpos.BaseIntegrationTest;
import tw.thoughtpos.domain.Goods;

public class GoodsControllerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private GoodsController goodsController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(goodsController).build();

    }

    @Test
    public void should_return_right_goods_given_barcode() throws Exception {
        mockMvc.perform(get("/goods/{barcode}", "ITEM000098"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.barcode").value("ITEM000098"))
                .andExpect(jsonPath("$.name").value("可口可乐"))
                .andExpect(jsonPath("$.price").value(2.50))
                .andExpect(jsonPath("$.unit").value("瓶"));

    }

    @Test
    public void should_save_right_goods() throws Exception {
        Goods goods = Goods.generateGoods("ITEM000077", "APPLE", 5.5d, "斤");
        String inputs = new ObjectMapper().writeValueAsString(goods);
        mockMvc.perform(post("/goods").contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8).content(inputs))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.barcode").value("ITEM000077"))
                .andExpect(jsonPath("$.name").value("APPLE"))
                .andExpect(jsonPath("$.price").value(5.5d))
                .andExpect(jsonPath("$.unit").value("斤"))
                .andDo(print());
    }
}
