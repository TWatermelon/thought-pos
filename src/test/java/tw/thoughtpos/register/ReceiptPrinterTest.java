package tw.thoughtpos.register;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Benefit;

public class ReceiptPrinterTest {

    private List<ShoppingItem> shoppingItems;
    private Receipt receipt;

    @Before
    public void setUp() throws Exception {
        shoppingItems = new ArrayList<>();
    }

    @Test
    public void should_print_receipt_without_promotions_goods() throws Exception {
        IPrinter printer = mock(IPrinter.class);
        ShoppingItem shoppingItem = generateShoppingItem("ITEM00001", 4,
                generateGoods("ITEM00001", 5.00d, "苹果", "斤"), generateBenefit(0d, 0, ""));
        shoppingItems.add(shoppingItem);
        receipt = new Receipt(shoppingItems);
        ReceiptPrinter.getInstance().print(receipt, printer);
        verify(printer).print(getExpectedContentWithoutPromotions());
    }

    @Test
    public void should_print_receipt_with_discount_promotions() {
        IPrinter printer = mock(IPrinter.class);
        ShoppingItem shoppingItem0 = generateShoppingItem("ITEM00001", 4,
                generateGoods("ITEM00001", 5.00d, "苹果", "斤"), generateBenefit(1.00d, 0, ""));
        ShoppingItem shoppingItem1 = generateShoppingItem("ITEM00006", 5,
                generateGoods("ITEM00006", 8.00d, "芒果", "斤"), generateBenefit(2.00d, 0, ""));
        ShoppingItem shoppingItem2 = generateShoppingItem("ITEM00003", 5,
                generateGoods("ITEM00003", 50.00d, "茶壶", "个"), generateBenefit(0d, 0, ""));

        shoppingItems.add(shoppingItem2);
        shoppingItems.add(shoppingItem0);
        shoppingItems.add(shoppingItem1);
        receipt = new Receipt(shoppingItems);
        ReceiptPrinter.getInstance().print(receipt, printer);
        verify(printer).print(getExpectedContentWithDiscountPromotions());
    }

    @Test
    public void should_print_receipt_with_buy_two_present_one_promotions() {
        IPrinter printer = mock(IPrinter.class);
        ShoppingItem shoppingItem0 = generateShoppingItem("ITEM00001", 4,
                generateGoods("ITEM00001", 5.00d, "苹果", "斤"), generateBenefit(0d, 0, ""));
        ShoppingItem shoppingItem1 = generateShoppingItem("ITEM00003", 3,
                generateGoods("ITEM00003", 5.00d, "可乐", "瓶"), generateBenefit(0d, 1, "买二赠一商品"));

        shoppingItems.add(shoppingItem0);
        shoppingItems.add(shoppingItem1);
        receipt = new Receipt(shoppingItems);
        ReceiptPrinter.getInstance().print(receipt, printer);
        verify(printer).print(getExpectedContentWithBuyTwoPresentOnePromotions());
    }

    @Test
    public void should_print_receipt_with_multiple_promotions_goods() throws Exception {
        IPrinter printer = mock(IPrinter.class);
        ShoppingItem shoppingItem0 = generateShoppingItem("ITEM00001", 4,
                generateGoods("ITEM00001", 5.00d, "苹果", "斤"), generateBenefit(1.00d, 0, ""));
        shoppingItems.add(shoppingItem0);
        ShoppingItem shoppingItem1 = generateShoppingItem("ITEM00002", 3,
                generateGoods("ITEM00002", 5.00d, "可乐", "瓶"), generateBenefit(0d, 1, "买二赠一商品"));
        shoppingItems.add(shoppingItem1);
        receipt = new Receipt(shoppingItems);
        ReceiptPrinter.getInstance().print(receipt, printer);
        verify(printer).print(getExpectedContentWithMultiplePromotions());
    }

    private String getExpectedContentWithDiscountPromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：茶壶,数量：5个,单价：50.00（元）,小计:250.00（元）").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:19.00（元）,节省1.00（元）").append("\n")
                .append("名称：芒果,数量：5斤,单价：8.00（元）,小计:38.00（元）,节省2.00（元）").append("\n")
                .append("----------").append("\n")
                .append("总计：307.00（元）").append("\n")
                .append("节省：3.00（元）").append("\n");
        return builder.toString();
    }

    private String getExpectedContentWithBuyTwoPresentOnePromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:20.00（元）").append("\n")
                .append("名称：可乐,数量：3瓶,单价：5.00（元）,小计:10.00（元）").append("\n")
                .append("----------").append("\n")
                .append("买二赠一商品：").append("\n")
                .append("名称：可乐,数量：1瓶").append("\n")
                .append("----------").append("\n")
                .append("总计：30.00（元）").append("\n")
                .append("节省：5.00（元）").append("\n");
        return builder.toString();
    }

    private String getExpectedContentWithoutPromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:20.00（元）").append("\n")
                .append("----------").append("\n")
                .append("总计：20.00（元）").append("\n");
        return builder.toString();
    }

    private String getExpectedContentWithMultiplePromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:19.00（元）,节省1.00（元）").append("\n")
                .append("名称：可乐,数量：3瓶,单价：5.00（元）,小计:10.00（元）").append("\n")
                .append("----------").append("\n")
                .append("买二赠一商品：").append("\n")
                .append("名称：可乐,数量：1瓶").append("\n")
                .append("----------").append("\n")
                .append("总计：29.00（元）").append("\n")
                .append("节省：6.00（元）").append("\n");
        return builder.toString();
    }

    private ShoppingItem generateShoppingItem(String barcode, int amount, Goods goods, Benefit benefit) {
        ShoppingItem shoppingItem = new ShoppingItem(barcode, amount);
        shoppingItem.setBenefit(benefit);
        shoppingItem.setGoods(goods);
        return shoppingItem;
    }

    private Benefit generateBenefit(double allowance, int saveAmount, String name) {
        Benefit benefit = new Benefit();
        benefit.setAllowance(allowance);
        benefit.setName(name);
        benefit.setSaveAmount(saveAmount);
        return benefit;
    }

    private Goods generateGoods(String barcode, double price, String name, String unit) {
        Goods goods = new Goods(barcode);
        goods.setPrice(price);
        goods.setName(name);
        goods.setUnit(unit);
        return goods;
    }
}
