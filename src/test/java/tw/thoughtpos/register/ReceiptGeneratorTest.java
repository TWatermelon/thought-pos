package tw.thoughtpos.register;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;


public class ReceiptGeneratorTest {
    @Test
    public void should_generate_right_receipt_given_shoppingitem_list() {
        List<ShoppingItem> shoppingItemList = new ArrayList<>();
        shoppingItemList.add(new ShoppingItem("ITEM000001", 3));
        shoppingItemList.add(new ShoppingItem("ITEM000003", 2));
        Receipt receipt = ReceiptGenerator
                .generate_receipt_given_shoppingitem_list(shoppingItemList);

        assertEquals(receipt.getTotalPrice(), 24d, 0.00001);
        assertEquals(receipt.getTotalAllowance(), 3d, 0.00001);
    }
}
