package tw.thoughtpos.promotions;

import tw.thoughtpos.parse.Item;

public interface Promotions {
    Benefit calculate(Item item);
}
