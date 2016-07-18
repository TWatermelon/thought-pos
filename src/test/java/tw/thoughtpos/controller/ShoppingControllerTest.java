package tw.thoughtpos.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.service.ShoppingService;

public class ShoppingControllerTest {

    private static final String BARCODE = "ITEM000001";
    @InjectMocks
    private ShoppingController itemController;

    @Mock
    private ShoppingService shoppingService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    @Ignore
    public void should_calculate_item_when_given_input_list() {
        List<ShoppingItem> items = asList(new ShoppingItem(BARCODE, 3));
        Receipt receipt = mock(Receipt.class);
        when(receipt.getTotalPrice()).thenReturn(15d);
        when(receipt.getTotalSave()).thenReturn(3d);
        when(shoppingService.generateReceipt(items)).thenReturn(receipt);

        ResponseEntity<?> response = itemController.generateReceipt(asList(BARCODE));

        assertThat(response.getStatusCode(), is(OK));
        assertThat(((Receipt) response.getBody()).getTotalPrice(), is(15d));
        assertThat(((Receipt) response.getBody()).getTotalSave(), is(3d));
    }
}
