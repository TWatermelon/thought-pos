package tw.thoughtpos.repository;

import static org.hamcrest.core.Is.is;

import java.util.Map;

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
    private DefaultPromotionsRepository promotionsRepository;
    private Promotions discountPromotions;
    private Promotions amountFreePromotions;


    @Before
    public void setUp() {
        discountPromotions = new DiscountPromotions("单品打折", "0.8d");
        amountFreePromotions = new AmountFreePromotions("买二赠一", "2 1");
    }

    @Test
    public void should_save_promotions_given_promotions_correctly() {
        promotionsRepository = new DefaultPromotionsRepository();
        promotionsRepository.getPromotionsMap().clear();

        promotionsRepository.savePromotions(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsRepository.savePromotions(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);
        Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();

        Assert.assertThat(promotionsMap.get(DISCOUNT_PROMOTIONS_CODE), is(discountPromotions));
        Assert.assertThat(promotionsMap.get(AMOUNT_FREE_PROMOTIONS_CODE), is(amountFreePromotions));
    }

    @Test
    public void should_find_correct_promotions_given_promotions_code() {
        promotionsRepository = new DefaultPromotionsRepository();
        promotionsRepository.getPromotionsMap().clear();
        Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        promotionsMap.put(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);

        Assert.assertThat(promotionsRepository
                .findPromotions(DISCOUNT_PROMOTIONS_CODE), is(discountPromotions));
        Assert.assertThat(promotionsRepository
                .findPromotions(AMOUNT_FREE_PROMOTIONS_CODE), is(amountFreePromotions));
    }

    @Test
    public void should_get_discount_promotions_given_barcode() {
        promotionsRepository = new DefaultPromotionsRepository();
        Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();
        promotionsMap.clear();
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);
        Map<String, String> barcodePromotionsCodeMap = promotionsRepository.getBarcodePromotionsCodeMap();
        barcodePromotionsCodeMap.clear();
        barcodePromotionsCodeMap.put(APPLE_BARCODE, DISCOUNT_PROMOTIONS_CODE);

        Promotions promotions = promotionsRepository.getPromotions(APPLE_BARCODE);

        Assert.assertThat(promotions, is(discountPromotions));
    }

    @Test
    public void should_get_free_amount_promotions_given_barcode() {
        promotionsRepository = new DefaultPromotionsRepository();
        Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();
        promotionsMap.clear();
        promotionsMap.put(AMOUNT_FREE_PROMOTIONS_CODE, amountFreePromotions);
        Map<String, String> barcodePromotionsCodeMap = promotionsRepository.getBarcodePromotionsCodeMap();
        barcodePromotionsCodeMap.clear();
        barcodePromotionsCodeMap.put(KEYRING_BARCODE, AMOUNT_FREE_PROMOTIONS_CODE);

        Promotions promotions = promotionsRepository.getPromotions(KEYRING_BARCODE);

        Assert.assertThat(promotions, is(amountFreePromotions));
    }

    @Test
    public void should_add_promotions_for_given_goods_correctly() {
        promotionsRepository = new DefaultPromotionsRepository();
        Map<String, Promotions> promotionsMap = promotionsRepository.getPromotionsMap();
        promotionsMap.clear();
        promotionsMap.put(DISCOUNT_PROMOTIONS_CODE, discountPromotions);

        promotionsRepository.addPromotions(APPLE_BARCODE, DISCOUNT_PROMOTIONS_CODE);

        Assert.assertThat(promotionsRepository
                .getBarcodePromotionsCodeMap().get(APPLE_BARCODE), is(DISCOUNT_PROMOTIONS_CODE));
    }
}
