package tw.thoughtpos.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.repository.DiscountPromotionRepository;

public class DiscountPromotionsServiceTest {
    private static final String BARCODE = "ITEM000001";
    private static final String NAME = "打折商品";
    @InjectMocks
    private DiscountPromotionsService discountPromotionsService;

    @Mock
    private DiscountPromotionRepository discountPromotionRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(discountPromotionRepository.findOne(BARCODE))
                .thenReturn(new DiscountPromotions(NAME, "0.95"));

    }

    @Test
    public void should_return_DiscountPromotions_given_barcode() {
        DiscountPromotions discountPromotions = discountPromotionsService.findPromotions(BARCODE);
        assertThat(discountPromotions.getName(), Is.is(NAME));
        assertThat(discountPromotions.getRate(), Is.is(0.95));

    }
}
