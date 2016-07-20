package tw.thoughtpos.repository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.promotions.Promotions;


public class PromotionsRepositoryTest {
    private static final String DISCOUNT_PROMOTIONS_CODE = "P0001";
    private static final String AMOUNT_FREE_PROMOTIONS_CODE = "P0002";
    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String KEYRING_BARCODE = "ITEM000002";
    private DefaultPromotionsRepository promotionsRepository = new DefaultPromotionsRepository();
    private Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();
    private Map<String, PriorityQueue<String>> barcodePromotionsCodeMap =
            promotionsRepository.getBarcodePromotionsCodeMap();
    private Promotions discountPromotions = new DiscountPromotions("单品打折", "0.8d");
    private Promotions amountFreePromotions = new AmountFreePromotions("买二赠一", "2 1");


    @Before
    public void setUp() {
        promotionsMap.clear();
        barcodePromotionsCodeMap.clear();
    }

    @Test
    public void should_save_promotions_given_promotions_correctly() {
        promotionsRepository.savePromotions(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsRepository.savePromotions(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);

        Assert.assertThat(promotionsMap.get(DISCOUNT_PROMOTIONS_CODE), is(discountPromotions));
        Assert.assertThat(promotionsMap.get(AMOUNT_FREE_PROMOTIONS_CODE), is(amountFreePromotions));
    }

    @Test
    public void should_find_correct_promotions_given_promotions_code() {
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsMap.put(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);

        Assert.assertThat(promotionsRepository
                .findPromotions(DISCOUNT_PROMOTIONS_CODE), is(discountPromotions));
        Assert.assertThat(promotionsRepository
                .findPromotions(AMOUNT_FREE_PROMOTIONS_CODE), is(amountFreePromotions));
    }

    @Test
    public void should_get_high_priority_promotions_given_barcode() {
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsMap.put(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);
        PriorityQueue<String> promotionsQueue = new PriorityQueue<>(promotionsRepository.getComparator());
        discountPromotions.setPriority(1);
        amountFreePromotions.setPriority(2);
        promotionsQueue.add(DISCOUNT_PROMOTIONS_CODE);
        promotionsQueue.add(AMOUNT_FREE_PROMOTIONS_CODE);
        barcodePromotionsCodeMap.put(APPLE_BARCODE, promotionsQueue);

        Promotions promotions = promotionsRepository.getPromotions(APPLE_BARCODE);
        Assert.assertThat(promotions, is(discountPromotions));

        promotionsQueue.clear();
        discountPromotions.setPriority(2);
        amountFreePromotions.setPriority(1);
        promotionsQueue.add(DISCOUNT_PROMOTIONS_CODE);
        promotionsQueue.add(AMOUNT_FREE_PROMOTIONS_CODE);
        promotions = promotionsRepository.getPromotions(APPLE_BARCODE);
        Assert.assertThat(promotions, is(amountFreePromotions));
    }

    @Test
    public void should_get_null_promotions_given_barcode() {
        assertEquals(promotionsRepository.getPromotions("abc"), null);
    }

    @Test
    public void should_add_promotions_for_given_goods_correctly() {
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsRepository.addPromotions(APPLE_BARCODE, DISCOUNT_PROMOTIONS_CODE);

        Assert.assertThat(promotionsRepository
                .getBarcodePromotionsCodeMap().get(APPLE_BARCODE).peek(), is(DISCOUNT_PROMOTIONS_CODE));
    }
}
