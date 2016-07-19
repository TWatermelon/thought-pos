package tw.thoughtpos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.promotions.Promotions;

@Repository
public class DefaultPromotionsRepository implements PromotionsRepository {

    private Map<String, Promotions> promotionsMap;
    private Map<String, String> barcodePromotionsCodeMap;

    public DefaultPromotionsRepository() {
        promotionsMap = new HashMap<>();
        promotionsMap.put("P0001", new DiscountPromotions("单品打折", "0.8d"));
        promotionsMap.put("P0002", new AmountFreePromotions("买二赠一", "2 1"));

        barcodePromotionsCodeMap = new HashMap<>();
        barcodePromotionsCodeMap.put("ITEM000001", "P0001");
        barcodePromotionsCodeMap.put("ITEM000002", "P0002");
    }

    @Override
    public void savePromotions(String promotionsCode, Promotions promotions) {
        this.promotionsMap.put(promotionsCode, promotions);
    }

    @Override
    public Promotions findPromotions(String promotionsCode) {
        return this.promotionsMap.get(promotionsCode);
    }

    @Override
    public void addPromotions(String barcode, String promotionsCode) {
        if (promotionsMap.containsKey(promotionsCode)) {
            this.barcodePromotionsCodeMap.put(barcode, promotionsCode);
        }
    }

    @Override
    public Promotions getPromotions(String barcode) {
        return this.promotionsMap.get(barcodePromotionsCodeMap.get(barcode));
    }

    public Map<String, Promotions> getPromotionsMap() {
        return promotionsMap;
    }

    public Map<String, String> getBarcodePromotionsCodeMap() {
        return barcodePromotionsCodeMap;
    }
}
