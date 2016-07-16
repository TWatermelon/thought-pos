package tw.thoughtpos.service;

import java.util.List;

import tw.thoughtpos.domain.ShoppingItem;

public interface ShoppingService {
    List<ShoppingItem> calculateItems(List<ShoppingItem> shoppingItems);
}
