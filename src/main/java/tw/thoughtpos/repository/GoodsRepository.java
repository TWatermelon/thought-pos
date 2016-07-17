package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.promotions.DiscountPromotions;

@Repository
public class GoodsRepository {

    private static Map<String, Goods> goodsMap;
    static {
        goodsMap = new HashMap<>();
        Goods apple = new Goods("ITEM000001");
        apple.setName("苹果");
        apple.setPrice(5d);
        apple.setUnit("斤");
        apple.setPromotions(new DiscountPromotions(0.8d));

        Goods keyring = new Goods("ITEM000003");
        keyring.setName("钥匙扣");
        keyring.setPrice(6d);
        keyring.setUnit("个");


        goodsMap.put("ITEM000001", apple);
        goodsMap.put("ITEM000003", keyring);
    }

    public static Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }
}

