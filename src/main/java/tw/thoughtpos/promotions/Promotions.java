package tw.thoughtpos.promotions;

import tw.thoughtpos.domain.ShoppingItem;

public interface Promotions {
    Benefit calculate(ShoppingItem item);
}
