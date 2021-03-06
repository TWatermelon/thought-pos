package tw.thoughtpos.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.register.IParser;
import tw.thoughtpos.service.ShoppingService;

public class ShoppingControllerTest {

    @InjectMocks
    private ShoppingController itemController;

    @Mock
    private ShoppingService shoppingService;

    @Mock
    private IParser iParser;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void should_prepare_benefits_when_given_input_list() {
        List<String> inputs = asList("ITEM000001-3");
        List<ShoppingItem> expectedItems = new ArrayList<>();
        when(iParser.parseToItem(inputs)).thenReturn(asList(new ShoppingItem("ITEM000001", 3)));
        when(shoppingService.bindGoods(asList(new ShoppingItem("ITEM000001", 3))))
                .thenReturn(asList(new ShoppingItem("ITEM000001", 3)));
        when(shoppingService.prepareBenefits(asList(new ShoppingItem("ITEM000001", 3))))
                .thenReturn(expectedItems);


        ResponseEntity<?> response = itemController.generateReceipt(inputs);

        verify(shoppingService).prepareBenefits(anyObject());
        assertThat(response.getStatusCode(), is(OK));
        assertThat(((Receipt) response.getBody()).getShoppingItems(), is(expectedItems));
    }
}
