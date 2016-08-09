package tw.thoughtpos.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.thoughtpos.BaseIntegrationTest;

public class ShoppingControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ShoppingController shoppingController;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(shoppingController).build();

    }

    @Test
    public void should_find_right_goods_given_barcode() throws Exception {
        mockMvc.perform(post("/shoppingItems").contentType(APPLICATION_JSON).content("[\"ITEM000098\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoppingItems", hasSize(1)))
                .andExpect(jsonPath("$.shoppingItems[0].barcode").value("ITEM000098")).andDo(print());

    }

}
