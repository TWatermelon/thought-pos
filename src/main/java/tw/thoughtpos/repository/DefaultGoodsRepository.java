package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;

@Repository
public class DefaultGoodsRepository implements GoodsRepository {
    private static Map<String, Goods> goodsMap;

    public DefaultGoodsRepository() {
        goodsMap = new HashMap<>();
        Goods apple = new Goods("ITEM000001");
        apple.setName("苹果");
        apple.setPrice(5d);
        apple.setUnit("斤");
        apple.setPromotions(new DiscountPromotions("单品打折", "0.8d"));

        Goods keyring = new Goods("ITEM000003");
        keyring.setName("钥匙环");
        keyring.setUnit("个");
        keyring.setPrice(6d);

        Goods cococola = new Goods("ITEM000002");
        cococola.setName("可口可乐");
        cococola.setPrice(2.5d);
        cococola.setUnit("瓶");
        keyring.setPromotions(new AmountFreePromotions("买二赠一", "2 1"));

        goodsMap.put("ITEM000001", apple);
        goodsMap.put("ITEM000002", cococola);
        goodsMap.put("ITEM000003", keyring);
    }

    public Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }
}

