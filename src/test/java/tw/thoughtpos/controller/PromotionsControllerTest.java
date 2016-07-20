package tw.thoughtpos.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.service.PromotionsService;

public class PromotionsControllerTest {
    private static final String PROMOTIONS_CODE = "P0001";
    private static final String BARCODE = "ITEM000001";

    @InjectMocks
    private PromotionsController promotionsController;

    @Mock
    private PromotionsService promotionsService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void should_save_promotions() {
        Promotions promotions = mock(Promotions.class);
        promotionsController.savePromotions(PROMOTIONS_CODE, promotions);
        verify(promotionsService).savePromotions(PROMOTIONS_CODE, promotions);
    }

    @Test
    public void should_find_promotions() {
        promotionsController.findPromotions(PROMOTIONS_CODE);
        verify(promotionsService).findPromotions(PROMOTIONS_CODE);
    }

    @Test
    public void should_add_promotions_for_goods_given_barcode() {
        promotionsController.addPromotions(BARCODE, PROMOTIONS_CODE);
        verify(promotionsService).addPromotions(BARCODE, PROMOTIONS_CODE);
    }

    @Test
    public void should_get_promotions_given_barcode() {
        promotionsController.getPromotions(BARCODE);
        verify(promotionsService).getPromotions(BARCODE);
    }
}
