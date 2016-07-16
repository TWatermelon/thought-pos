package tw.thoughtpos.domain;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ShoppingItem> shoppingItems = new ArrayList();

    public Receipt(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public double getTotalPrice() {
        return shoppingItems.stream().mapToDouble(ShoppingItem::getSubtotal).sum();
    }

    public double getTotalAllowance() {
        return shoppingItems.stream().mapToDouble(ShoppingItem::getAllowance).sum();
    }
}
