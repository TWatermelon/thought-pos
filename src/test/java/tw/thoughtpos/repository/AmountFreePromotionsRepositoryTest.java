package tw.thoughtpos.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.promotions.AmountFreePromotions;

public class AmountFreePromotionsRepositoryTest {
    private static final String BARCODE = "ITEM000001";
    private static final String NAME = "买二赠一商品";
    private AmountFreePromotionsRepository amountFreePromotionsRepository;

    @Before
    public void setUp() throws Exception {

        amountFreePromotionsRepository = new AmountFreePromotionsRepository();

    }

    @Test
    public void should_save_promotions_given_barcode() {
        AmountFreePromotions amountFreePromotions = new AmountFreePromotions(NAME, "2 1");
        amountFreePromotionsRepository.save(BARCODE, amountFreePromotions);
        Map<String, AmountFreePromotions> mapper = amountFreePromotionsRepository.getMapper();
        assertThat(mapper.containsKey(BARCODE), is(true));
        AmountFreePromotions amountFreePromotionsActual = mapper.get(BARCODE);
        assertThat(amountFreePromotionsActual.getName(), is(NAME));
        assertThat(amountFreePromotionsActual.getBuyAmount(), is(2));
        assertThat(amountFreePromotionsActual.getFreeAmount(), is(1));
    }

    @Test
    public void should_return_right_promotions_given_barcode() {

        amountFreePromotionsRepository.save(BARCODE, new AmountFreePromotions(NAME, "2 1"));
        AmountFreePromotions amountFreePromotions = amountFreePromotionsRepository.findOne(BARCODE);
        assertThat(amountFreePromotions.getName(), is(NAME));
        assertThat(amountFreePromotions.getBuyAmount(), is(2));
        assertThat(amountFreePromotions.getFreeAmount(), is(1));

    }
}
