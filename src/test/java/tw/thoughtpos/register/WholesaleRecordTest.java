package tw.thoughtpos.register;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class WholesaleRecordTest {

    private static final String BARCODE = "ITEM00001";

    @Test
    public void should_return_discount_info() {
        WholesaleRecord wholesaleRecord = new WholesaleRecord();
        ShoppingItem shoppingItem = new ShoppingItem(BARCODE, 5);
        Goods goods = new Goods(BARCODE);
        goods.setName("可乐");
        goods.setUnit("瓶");
        shoppingItem.setGoods(goods);
        wholesaleRecord.setShoppingItem(shoppingItem);
        assertThat(wholesaleRecord.showRecord(), is("名称：" + "可乐," + "数量：5瓶"));

    }
}
