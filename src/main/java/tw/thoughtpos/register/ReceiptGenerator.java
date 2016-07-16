package tw.thoughtpos.register;

import java.util.List;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.utils.ShoppingItemHandler;

public class ReceiptGenerator {
    public static Receipt generateReceipt(
            List<ShoppingItem> shoppingItems) {
        shoppingItems.forEach(ShoppingItemHandler::bindGoods);
        ShoppingItemHandler.calculateBenefits(shoppingItems);

        Receipt receipt = new Receipt(shoppingItems);

        return receipt;
    }
}
