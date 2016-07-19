package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;

@Repository
public class DefaultGoodsRepository implements GoodsRepository {
    private static Map<String, Goods> goodsMap;

    public DefaultGoodsRepository() {
        goodsMap = new HashMap<>();
        Goods apple = new Goods("ITEM000001");
        apple.setName("苹果");
        apple.setPrice(5d);
        apple.setUnit("斤");

        Goods keyring = new Goods("ITEM000003");
        keyring.setName("钥匙环");
        keyring.setUnit("个");
        keyring.setPrice(6d);

        Goods cola = new Goods("ITEM000002");
        cola.setName("可口可乐");
        cola.setPrice(2.5d);
        cola.setUnit("瓶");

        goodsMap.put("ITEM000001", apple);
        goodsMap.put("ITEM000002", cola);
        goodsMap.put("ITEM000003", keyring);
    }

    public Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }
}

