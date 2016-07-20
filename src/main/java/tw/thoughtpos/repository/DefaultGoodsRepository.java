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
//        goodsMap.put("ITEM000001", generateGoods("ITEM000001", "苹果", 5d, "斤"));
//        goodsMap.put("ITEM000002", generateGoods("ITEM000002", "可口可乐", 2.5d, "瓶"));
//        goodsMap.put("ITEM000003", generateGoods("ITEM000003", "钥匙环", 6d, "个"));
//        goodsMap.put("ITEM000004", generateGoods("ITEM000004", "猪肉", 13d, "斤"));
//        goodsMap.put("ITEM000005", generateGoods("ITEM000005", "篮球", 199d, "个"));
    }

    public Map<String, Goods> getGoodsMap() {
        return goodsMap;
    }

    private Goods generateGoods(String item000001, String name, double price, String unit) {
        Goods goods = new Goods(item000001);
        goods.setName(name);
        goods.setPrice(price);
        goods.setUnit(unit);
        return goods;
    }

    public Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }

    @Override
    public Goods addGoods(String barcode, Goods goods) {
        return goodsMap.put(barcode, goods);
    }
}

