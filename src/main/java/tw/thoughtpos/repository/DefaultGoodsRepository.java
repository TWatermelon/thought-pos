package tw.thoughtpos.repository;

import static tw.thoughtpos.domain.Goods.generateGoods;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;

@Repository
public class DefaultGoodsRepository implements GoodsRepository {
    private static Map<String, Goods> goodsMap;

    public DefaultGoodsRepository() {
        goodsMap = new HashMap<>();
        goodsMap.put("ITEM000099", generateGoods("ITEM000099", "苹果", 5d, "斤"));
        goodsMap.put("ITEM000098", generateGoods("ITEM000098", "可口可乐", 2.5d, "瓶"));
        goodsMap.put("ITEM000097", generateGoods("ITEM000097", "烤鸭", 25d, "只"));
    }

    public Map<String, Goods> getGoodsMap() {
        return goodsMap;
    }

    @Override
    public Goods findGoods(String barcode) {
        return goodsMap.get(barcode);
    }

    @Override
    public Goods addGoods(String barcode, Goods goods) {
        return goodsMap.put(barcode, goods);
    }
}

