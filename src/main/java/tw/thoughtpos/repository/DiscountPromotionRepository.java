package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.promotions.DiscountPromotions;

@Repository
public class DiscountPromotionRepository {
    private Map<String, DiscountPromotions> discountPromotionsMap = new HashMap<>();

    public DiscountPromotions findOne(String barcode) {
        return discountPromotionsMap.get(barcode);
    }

    public void save(String barcode, DiscountPromotions discountPromotions) {
        discountPromotionsMap.put(barcode, discountPromotions);
    }

    public Map<String, DiscountPromotions> getMapper() {
        return discountPromotionsMap;
    }
}
