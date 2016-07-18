package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.promotions.DiscountPromotions;

@Repository
public class DefaultGoodsRepository implements GoodsRepository {
    private static Map<String, Goods> goodsMap;

    public DefaultGoodsRepository() {
        goodsMap = new HashMap<>();
        Goods apple = new Goods("ITEM000001");
        apple.setName("苹果");
        apple.setPrice(5d);
        apple.setPromotions(new DiscountPromotions("单品打折", "0.8d"));

        Goods keyring = new Goods("ITEM000003");
        keyring.setName("钥匙环");
        keyring.setPrice(6d);

        goodsMap.put("ITEM000001", apple);
        goodsMap.put("ITEM000003", keyring);
    }

    public Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }
}


