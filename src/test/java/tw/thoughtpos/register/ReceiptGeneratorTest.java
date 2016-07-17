package tw.thoughtpos.register;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;


public class ReceiptGeneratorTest {
    @Test
    public void should_generate_right_receipt_given_shopping_item_list() {
        List<ShoppingItem> shoppingItems = new ArrayList<>();
        shoppingItems.add(new ShoppingItem("ITEM000001", 3));
        shoppingItems.add(new ShoppingItem("ITEM000003", 2));
        Receipt receipt = ReceiptGenerator.generateReceipt(shoppingItems);

        assertEquals(27d, receipt.getTotalPrice(), 0.00001);
        assertEquals(3d, receipt.getTotalSave(), 0.00001);
    }
}
