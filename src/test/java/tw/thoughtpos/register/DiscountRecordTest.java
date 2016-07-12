package tw.thoughtpos.register;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.ShoppingItem;

public class DiscountRecordTest {
    @Test
    public void should_return_discount_info() {
        DiscountRecord discountRecord = new DiscountRecord("七五折");
        discountRecord.setDiscountType("七五折");
        ShoppingItem shoppingItem = new ShoppingItem("ITEM00001", 5);
        Goods goods = new Goods("ITEM00001");
        goods.setName("苹果");
        shoppingItem.setGoods(goods);
        discountRecord.setShoppingItem(shoppingItem);
        assertThat(discountRecord.showRecord(), is("名称：" + "苹果," + "折扣：七五折"));

    }
}
