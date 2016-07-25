package tw.thoughtpos.register;

import java.util.List;

import tw.thoughtpos.domain.ShoppingItem;

public interface IParser {
    List<ShoppingItem> parseToItem(List<String> input);
}
