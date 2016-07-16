package tw.thoughtpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.ShoppingItem;

@Service
public class DefaultShoppingService implements ShoppingService {
    @Override
    public List<ShoppingItem> calculateItems(List<ShoppingItem> shoppingItems) {
        return null;
    }
}
