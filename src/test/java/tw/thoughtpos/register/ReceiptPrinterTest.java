package tw.thoughtpos.register;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        receipt = new Receipt();
        shoppingItems = new ArrayList<>();
    }


    @Test
    public void should_print_receipt_without_promotions_goods() throws Exception {
        PosPrinter printer = mock(PosPrinter.class);
        ShoppingItem shoppingItem = getShoppingItem("ITEM00001", 4,
                createGoods("ITEM00001", 5.00d, "苹果", "斤"), createBenefit(0.00));
        shoppingItems.add(shoppingItem);
        receipt.setShoppingItems(shoppingItems);
        ReceiptPrinter.print(receipt, printer);
        verify(printer).print(getExpectedContentWithoutPromotions());
    }

    @Test
    public void should_print_receipt_with_multiple_promotions_goods() throws Exception {
        PosPrinter printer = mock(PosPrinter.class);
        Map<String, List<Record>> mapper = new LinkedHashMap<>();
        List<Record> wholesaleRecords = new ArrayList<>();
        wholesaleRecords.add(createWholesaleRecord());
        mapper.put("批发价出售商品", wholesaleRecords);
        List<Record> discountRecords = new ArrayList<>();
        discountRecords.add(createDiscountRecord());
        mapper.put("单品打折商品", discountRecords);
        receipt.setMapper(mapper);

        ShoppingItem shoppingItem0 = getShoppingItem("ITEM00001", 4,
                createGoods("ITEM00001", 5.00d, "苹果", "斤"), createBenefit(5.00));
        shoppingItems.add(shoppingItem0);
        ShoppingItem shoppingItem1 = getShoppingItem("ITEM00002", 12,
                createGoods("ITEM00002", 5.00d, "可乐", "瓶"), createBenefit(3.00));
        shoppingItems.add(shoppingItem1);

        receipt.setShoppingItems(shoppingItems);
        ReceiptPrinter.print(receipt, printer);
        verify(printer).print(getExpectedContentWithMultiplePromotions());
    }

    @Test
    public void should_print_receipt_with_full_free_promotions() {
        PosPrinter printer = mock(PosPrinter.class);
        ShoppingItem shoppingItem0 = getShoppingItem("ITEM00001", 4,
                createGoods("ITEM00001", 5.00d, "苹果", "斤"), createBenefit(0.00));
        Map<String, List<Record>> mapper = new LinkedHashMap<>();
        List<Record> fullFreeRecords = new ArrayList<>();
        fullFreeRecords.add(createFullFreeRecord(shoppingItem0));
        receipt.setMapper(mapper);
        mapper.put("不参与优惠商品", fullFreeRecords);

        ShoppingItem shoppingItem = getShoppingItem("ITEM00003", 5,
                createGoods("ITEM00003", 50.00d, "茶壶", "个"), createBenefit(0.00));

        shoppingItems.add(shoppingItem);
        shoppingItems.add(shoppingItem0);
        receipt.setShoppingItems(shoppingItems);
        receipt.setOrderSaveOfFullFree(20.00d);
        receipt.setTotalMoneyOfFullFreeGoods(230.00d);
        ReceiptPrinter.print(receipt, printer);
        verify(printer).print(getExpectedContentWithFullFreePromotions());
    }

    private String getExpectedContentWithFullFreePromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：茶壶,数量：5个,单价：50.00（元）,小计:250.00（元）").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:20.00（元）").append("\n")
                .append("----------").append("\n")
                .append("不参与优惠商品：").append("\n")
                .append("名称：苹果,价格：20.00（元）").append("\n")
                .append("参与优惠总价：230.00（元）,优惠：20.00（元）").append("\n")
                .append("----------").append("\n")
                .append("总计：250.00（元）").append("\n")
                .append("节省：20.00（元）").append("\n");
        return builder.toString();
    }

    private Record createFullFreeRecord(ShoppingItem shoppingItem) {
        FullFreeRecord fullFreeRecord = new FullFreeRecord();
        fullFreeRecord.setShoppingItem(shoppingItem);
        return fullFreeRecord;
    }

    private String getExpectedContentWithMultiplePromotions() {
        StringBuilder builder = new StringBuilder();
        builder.append("***<没钱赚商店>购物清单***").append("\n")
                .append("名称：苹果,数量：4斤,单价：5.00（元）,小计:15.00（元）,优惠5.00（元）").append("\n")
                .append("名称：可乐,数量：12瓶,单价：5.00（元）,小计:57.00（元）,优惠3.00（元）").append("\n")
                .append("----------").append("\n")
                .append("批发价出售商品：").append("\n")
                .append("名称：可乐,数量：12瓶").append("\n")
                .append("----------").append("\n")
                .append("单品打折商品：").append("\n")
                .append("名称：苹果,折扣：七五折").append("\n")
                .append("----------").append("\n")
                .append("总计：72.00（元）").append("\n")
                .append("节省：8.00（元）").append("\n");
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

    private ShoppingItem getShoppingItem(String barcode, int amount, Goods goods, Benefit benefit) {
        ShoppingItem shoppingItem = new ShoppingItem(barcode, amount);
        shoppingItem.setBenefit(benefit);
        shoppingItem.setGoods(goods);
        return shoppingItem;
    }

    private DiscountRecord createDiscountRecord() {
        DiscountRecord discountRecord = new DiscountRecord("七五折");
        ShoppingItem shoppingItem = getShoppingItem("ITEM00001", 4,
                createGoods("ITEM00001", 5.00d, "苹果", "斤"), createBenefit(5.00));
        discountRecord.setShoppingItem(shoppingItem);
        return discountRecord;
    }

    private WholesaleRecord createWholesaleRecord() {
        WholesaleRecord wholesaleRecord = new WholesaleRecord();
        ShoppingItem shoppingItem = getShoppingItem("ITEM00002", 12,
                createGoods("ITEM00002", 5.00d, "可乐", "瓶"), createBenefit(2.00));
        wholesaleRecord.setShoppingItem(shoppingItem);
        return wholesaleRecord;
    }

    private Benefit createBenefit(double allowance) {
        Benefit benefit = new Benefit();
        benefit.setAllowance(allowance);
        return benefit;
    }

    private Goods createGoods(String barcode, double price, String name, String unit) {
        Goods goods = new Goods(barcode);
        goods.setPrice(price);
        goods.setName(name);
        goods.setUnit(unit);
        return goods;
    }
}
