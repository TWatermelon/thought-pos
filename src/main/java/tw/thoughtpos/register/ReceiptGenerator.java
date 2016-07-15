package tw.thoughtpos.register;

import java.util.List;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.utils.ShoppingItemHandler;

public class ReceiptGenerator {
    public static Receipt generate_receipt_given_shoppingitem_list(
            List<ShoppingItem> shoppingItems) {
        shoppingItems.forEach(ShoppingItemHandler::bind_goods_to_shoppingitem);
        ShoppingItemHandler.calculate_benefits_for_shoppingitem_list(shoppingItems);

        double totalPrice = 0d, totalAllowance = 0d;
        for (ShoppingItem shoppingItem : shoppingItems) {
            totalPrice += shoppingItem.getSubtotalPrice();
            totalAllowance += shoppingItem.getAllowance();
        }

        Receipt receipt = new Receipt();
        receipt.setShoppingItems(shoppingItems);
        receipt.setTotalPrice(totalPrice);
        receipt.setTotalAllowance(totalAllowance);

        return receipt;
    }
}
