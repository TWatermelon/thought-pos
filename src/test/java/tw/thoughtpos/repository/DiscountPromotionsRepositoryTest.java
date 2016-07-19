package tw.thoughtpos.repository;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.promotions.DiscountPromotions;

public class DiscountPromotionsRepositoryTest {

    private static final String BARCODE = "ITEM000001";
    private static final String NAME = "打折商品";
    private DiscountPromotionRepository discountPromotionRepository;


    @Before
    public void setUp() throws Exception {
        discountPromotionRepository = new DiscountPromotionRepository();

    }

    @Test
    public void should_add_right_promotions() {
        DiscountPromotions discountPromotions = new DiscountPromotions(NAME, "0.95");
        discountPromotionRepository.save(BARCODE, discountPromotions);
        Map<String, DiscountPromotions> discountPromotionRepositoryMapper
                = discountPromotionRepository.getMapper();
        assertThat(discountPromotionRepositoryMapper.containsKey(BARCODE), is(true));
        DiscountPromotions discountPromotionsActual = discountPromotionRepositoryMapper.get(BARCODE);
        assertThat(discountPromotionsActual.getName(), is(NAME));
        assertThat(discountPromotionsActual.getRate(), is(0.95));

    }

    @Test
    public void should_return_right_promotions_given_barcode() {
        discountPromotionRepository.save(BARCODE, new DiscountPromotions(NAME, "0.95"));
        DiscountPromotions discountPromotions = discountPromotionRepository.findOne(BARCODE);
        assertThat(discountPromotions.getName(), is(NAME));
        assertThat(discountPromotions.getRate(), is(0.95));

    }
}
