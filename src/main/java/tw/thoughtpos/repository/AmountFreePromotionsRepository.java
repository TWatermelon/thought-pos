package tw.thoughtpos.repository;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.promotions.AmountFreePromotions;

@Repository
public class AmountFreePromotionsRepository {
    private Map<String, AmountFreePromotions> mapper = new HashMap<>();

    public AmountFreePromotionsRepository() {
        mapper.put("ITEM000002", new AmountFreePromotions("买二赠一商品", "2 1"));
        mapper.put("ITEM000003", new AmountFreePromotions("买二赠一商品", "2 1"));
    }

    public void save(String barcode, AmountFreePromotions promotions) {
        mapper.put(barcode, promotions);
    }

    public AmountFreePromotions findOne(String barcode) {
        return mapper.get(barcode);
    }

    public Map<String, AmountFreePromotions> getMapper() {
        return mapper;
    }
}
