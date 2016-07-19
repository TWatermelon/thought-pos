package tw.thoughtpos.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.repository.AmountFreePromotionsRepository;
import tw.thoughtpos.repository.DiscountPromotionRepository;

public class DiscountPromotionsServiceTest {
    private static final String BARCODE = "ITEM000001";
    private static final String NAME = "打折商品";
    private static final String BUY_TWO_PRESENT_ONE = "买二赠一商品";
    @InjectMocks
    private DefaultPromotionsService discountPromotionsService;

    @Mock
    private DiscountPromotionRepository discountPromotionRepository;

    @Mock
    private AmountFreePromotionsRepository amountFreePromotionsRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }

    @Test
    public void should_return_DiscountPromotions_given_barcode() {
        when(discountPromotionRepository.findOne(BARCODE))
                .thenReturn(new DiscountPromotions(NAME, "0.95"));
        DiscountPromotions discountPromotions =
                (DiscountPromotions) discountPromotionsService.findPromotions(BARCODE);
        assertThat(discountPromotions.getName(), is(NAME));
        assertThat(discountPromotions.getRate(), is(0.95));

    }

    @Test
    public void should_return_DiscountPromotions_given_barcode_with_two_promotions() {
        when(discountPromotionRepository.findOne(BARCODE))
                .thenReturn(new DiscountPromotions(NAME, "0.95"));
        when(amountFreePromotionsRepository.findOne(BARCODE))
                .thenReturn(new AmountFreePromotions(BUY_TWO_PRESENT_ONE, "2 1"));
        DiscountPromotions discountPromotions =
                (DiscountPromotions) discountPromotionsService.findPromotions(BARCODE);
        assertThat(discountPromotions.getName(), is(NAME));
        assertThat(discountPromotions.getRate(), is(0.95));

    }

    @Test
    public void should_return_AmountFreePromotions_given_barcode() {
        when(discountPromotionRepository.findOne(BARCODE))
                .thenReturn(null);
        when(amountFreePromotionsRepository.findOne(BARCODE))
                .thenReturn(new AmountFreePromotions(BUY_TWO_PRESENT_ONE, "2 1"));
        AmountFreePromotions amountFreePromotions =
                (AmountFreePromotions) discountPromotionsService.findPromotions(BARCODE);
        assertThat(amountFreePromotions.getName(), is(BUY_TWO_PRESENT_ONE));
        assertThat(amountFreePromotions.getBuyAmount(), is(2));
        assertThat(amountFreePromotions.getFreeAmount(), is(1));

    }

    @Test
    public void should_return_null_given_barcode() {
        when(discountPromotionRepository.findOne(BARCODE))
                .thenReturn(null);
        when(amountFreePromotionsRepository.findOne(BARCODE))
                .thenReturn(null);
        discountPromotionsService.findPromotions(BARCODE);
        assertThat(discountPromotionsService.findPromotions(BARCODE) == null, is(true));

    }
}
