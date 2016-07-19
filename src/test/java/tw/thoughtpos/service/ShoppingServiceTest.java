package tw.thoughtpos.service;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Benefit;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.GoodsRepository;
import tw.thoughtpos.repository.PromotionsRepository;

public class ShoppingServiceTest {
    private static final String APPLE_BARCODE = "ITEM000001";
    private static final String APPLE_NAME = "苹果";
    private static final double APPLE_PRICE = 5d;
    private static final int APPLE_AMOUNT = 3;
    private static final double APPLE_ALLOWANCE = 0.75d;
    private static final String DISCOUNT_NAME = "打折商品";
    private static final String DISCOUNT_DETAILS = "折扣：八折";


    @InjectMocks
    private DefaultShoppingService shoppingService;

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private PromotionsRepository promotionsRepository;

    @Mock
    private DefaultPromotionsService promotionsService;

    @Before
    public void setUp() {
        initMocks(this);
        when(goodsRepository.findGoods(APPLE_BARCODE))
                .thenReturn(createGoods(APPLE_BARCODE, APPLE_NAME, APPLE_PRICE));
        when(promotionsService.findPromotions(APPLE_BARCODE))
                .thenReturn(new DiscountPromotions("打折商品", "0.95"));
    }

    @Test
    public void should_bind_the_right_goods_for_shopping_item_list() {
        List<ShoppingItem> shoppingItems = createShoppingItemList();
        shoppingItems = shoppingService.bindGoods(shoppingItems);

        assertThat(shoppingItems.get(0).getGoods().getName(), is(APPLE_NAME));
    }

    @Test
    public void should_prepare_all_of_the_right_benefits_for_shopping_item_list() {
        Benefit expectedBenefit = createBenefit(APPLE_ALLOWANCE, DISCOUNT_NAME, DISCOUNT_DETAILS);
        List<ShoppingItem> shoppingItemList = createShoppingItemList();

        Promotions promotions = mock(Promotions.class);
        when(promotions.prepareBenefit(shoppingItemList.get(0))).thenReturn(expectedBenefit);
        when(promotionsRepository.getPromotions(APPLE_BARCODE))
                .thenReturn(promotions);

        shoppingService.prepareBenefits(shoppingItemList);

        assertBenefit(shoppingItemList.get(0).getBenefit(), expectedBenefit);
    }

    @Test
    public void should_generate_right_receipt_given_shopping_item_list() {
        List<ShoppingItem> shoppingItems = createShoppingItemList();
        shoppingItems.get(0).setBenefit(createBenefit(APPLE_ALLOWANCE, DISCOUNT_NAME, DISCOUNT_DETAILS));

        Receipt receipt = shoppingService.generateReceipt(shoppingItems);

        assertEquals(15d, receipt.getTotalPrice(), 0.00001);
        assertEquals(0.75d, receipt.getTotalSave(), 0.00001);
    }

    private Benefit createBenefit(double allowance, String name, String details) {
        Benefit benefit = new Benefit();
        benefit.setAllowance(allowance);
        benefit.setName(name);
        return benefit;
    }

    private void assertBenefit(Benefit benefit, Benefit expected) {
        assertEquals(expected.getAllowance(), benefit.getAllowance(), 0.00001);
        assertThat(benefit.getName(), is(expected.getName()));
    }

    private List<ShoppingItem> createShoppingItemList() {
        return asList(createShoppingItem(APPLE_BARCODE, APPLE_NAME, APPLE_PRICE, APPLE_AMOUNT));
    }

    private ShoppingItem createShoppingItem(String barcode, String name, double price, int amount) {
        Goods goods = createGoods(barcode, name, price);
        ShoppingItem shoppingItem = new ShoppingItem(barcode, amount);
        shoppingItem.setGoods(goods);
        return shoppingItem;
    }

    private Goods createGoods(String barcode, String name, double price) {
        Goods goods = new Goods(barcode);
        goods.setName(name);
        goods.setPrice(price);
        return goods;
    }
}
