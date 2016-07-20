package tw.thoughtpos;

import static java.util.Arrays.asList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tw.thoughtpos.controller.GoodsController;
import tw.thoughtpos.controller.PromotionsController;
import tw.thoughtpos.controller.ShoppingController;
import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.register.ConsolePrinter;
import tw.thoughtpos.register.ReceiptPrinter;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PosApplication.class)
@WebAppConfiguration
public class PosSystem {
    @Autowired
    private ShoppingController shoppingController;

    @Autowired
    private PromotionsController promotionsController;

    @Autowired
    private GoodsController goodsController;

    @Before
    public void setUp() {
        goodsController.addGoods(generateGoods("ITEM000001", "苹果", 5d, "斤"));
        goodsController.addGoods(generateGoods("ITEM000002", "可口可乐", 2.5d, "瓶"));
        goodsController.addGoods(generateGoods("ITEM000003", "钥匙环", 6d, "个"));
        goodsController.addGoods(generateGoods("ITEM000004", "猪肉", 13d, "斤"));
        goodsController.addGoods(generateGoods("ITEM000005", "篮球", 199d, "个"));

        promotionsController.savePromotions("P0001", new DiscountPromotions("单品打折", "0.95d"));
        promotionsController.savePromotions("P0002", new AmountFreePromotions("买二赠一商品", "2 1"));

        promotionsController.addPromotions("ITEM000001", "P0001");
        promotionsController.addPromotions("ITEM000002", "P0002");
    }

    private Goods generateGoods(String item000001, String name, double price, String unit) {
        Goods goods = new Goods(item000001);
        goods.setName(name);
        goods.setPrice(price);
        goods.setUnit(unit);
        return goods;
    }

    @Ignore
    @Test
    public void output_result() {
        List<String> inputs = asList("ITEM000001",
                "ITEM000002-8", "ITEM000003-5", "ITEM000004");
        ReceiptPrinter.getInstance().print((Receipt) shoppingController.generateReceipt(inputs).getBody(),
                new ConsolePrinter());
    }
}
