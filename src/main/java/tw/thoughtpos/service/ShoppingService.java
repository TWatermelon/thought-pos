package tw.thoughtpos.service;

import java.util.List;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;

public interface ShoppingService {
    List<ShoppingItem> bindGoods(List<ShoppingItem> shoppingItems);

    List<ShoppingItem> prepareBenefits(List<ShoppingItem> shoppingItems);

    Receipt generateReceipt(List<ShoppingItem> shoppingItems);
}
