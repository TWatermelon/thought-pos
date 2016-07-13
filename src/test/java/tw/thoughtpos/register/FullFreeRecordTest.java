package tw.thoughtpos.register;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class FullFreeRecordTest {
    @Test
    public void should_return_discount_info() {
        FullMinusRecord fullFreeRecord = new FullMinusRecord();
        ShoppingItem shoppingItem = new ShoppingItem("ITEM00001", 5);
        Goods goods = new Goods("ITEM00001");
        goods.setName("苹果");
        goods.setPrice(4.00d);
        shoppingItem.setGoods(goods);
        fullFreeRecord.setShoppingItem(shoppingItem);
        assertThat(fullFreeRecord.showRecord(), is("名称：" + "苹果," + "价格：20.00（元）"));
    }
}
