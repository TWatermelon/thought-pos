package tw.thoughtpos.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.PromotionsRepository;

public class DefaultPromotionsServiceTest {
    private static final String PROMOTIONS_CODE = "P0001";
    private static final String BARCODE = "ITEM000001";

    @InjectMocks
    private DefaultPromotionsService promotionsService;

    @Mock
    private PromotionsRepository promotionsRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void should_save_promotions() {
        Promotions promotions = mock(Promotions.class);
        promotionsService.savePromotions(PROMOTIONS_CODE, promotions);
        verify(promotionsRepository).savePromotions(PROMOTIONS_CODE, promotions);
    }

    @Test
    public void should_find_promotions() {
        promotionsService.findPromotions(PROMOTIONS_CODE);
        verify(promotionsRepository).findPromotions(PROMOTIONS_CODE);
    }

    @Test
    public void should_add_promotions_for_goods_given_barcode() {
        promotionsService.addPromotions(BARCODE, PROMOTIONS_CODE);
        verify(promotionsRepository).addPromotions(BARCODE, PROMOTIONS_CODE);
    }

    @Test
    public void should_get_promotions_given_barcode() {
        promotionsService.getPromotions(BARCODE);
        verify(promotionsRepository).getPromotions(BARCODE);

    }
}
