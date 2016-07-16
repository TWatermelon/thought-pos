package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.promotions.DiscountPromotions;

public class GoodsRepository {

    private static Map<String, Goods> goodsMap;
    static {
        goodsMap = new HashMap<>();
        Goods apple = new Goods("ITEM000001");
        apple.setName("apple");
        apple.setPrice(5d);
        apple.setPromotions(new DiscountPromotions(0.8d));

        Goods keyring = new Goods("ITEM000003");
        keyring.setName("keyring");
        keyring.setPrice(6d);


        goodsMap.put("ITEM000001", apple);
        goodsMap.put("ITEM000003", keyring);
    }

    public static Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }
}
