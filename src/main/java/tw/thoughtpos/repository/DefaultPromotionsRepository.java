package tw.thoughtpos.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Repository;

import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.promotions.Promotions;

@Repository
public class DefaultPromotionsRepository implements PromotionsRepository {

    private Map<String, Promotions> promotionsMap;
    private Map<String, PriorityQueue<String>> barcodePromotionsCodeMap;
    private Comparator<String> promotionsComparator;

    public DefaultPromotionsRepository() {
        promotionsMap = new HashMap<>();
        barcodePromotionsCodeMap = new HashMap<>();
        promotionsComparator = (code1, code2) -> {
            int priority1 = findPromotions(code1).getPriority();
            int priority2 = findPromotions(code2).getPriority();
            return priority1 < priority2 ? -1 : 1;
        };

        promotionsMap.put("P0099", new DiscountPromotions("单品打折", "0.95d"));
        promotionsMap.put("P0098", new AmountFreePromotions("买二赠一商品", "2 1"));


        PriorityQueue<String> applePromotionsPriorityQueue = new PriorityQueue<>(promotionsComparator);
        applePromotionsPriorityQueue.add("P0099");
        PriorityQueue<String> keyringPromotionsPriorityQueue = new PriorityQueue<>(promotionsComparator);
        keyringPromotionsPriorityQueue.add("P0098");
        barcodePromotionsCodeMap.put("ITEM000099", applePromotionsPriorityQueue);
        barcodePromotionsCodeMap.put("ITEM000098", keyringPromotionsPriorityQueue);
    }

    @Override
    public Promotions savePromotions(String promotionsCode, Promotions promotions) {
        this.promotionsMap.put(promotionsCode, promotions);
        return promotions;
    }

    @Override
    public Promotions findPromotions(String promotionsCode) {
        return this.promotionsMap.get(promotionsCode);
    }

    @Override
    public Promotions addPromotions(String barcode, String promotionsCode) {
        if (promotionsMap.containsKey(promotionsCode)) {
            PriorityQueue<String> promotions = barcodePromotionsCodeMap.get(barcode);
            if (promotions == null) {
                promotions = new PriorityQueue<>(promotionsComparator);
                promotions.add(promotionsCode);
            } else if (!promotions.contains(promotionsCode)) {
                promotions.add(promotionsCode);
            }
            this.barcodePromotionsCodeMap.put(barcode, promotions);
        }
        return promotionsMap.get(promotionsCode);
    }

    @Override
    public Promotions getPromotions(String barcode) {
        if (barcodePromotionsCodeMap.get(barcode) != null) {
            return this.promotionsMap.get(barcodePromotionsCodeMap.get(barcode).peek());
        } else {
            return null;
        }
    }

    public Map<String, Promotions> getPromotionsMap() {
        return promotionsMap;
    }

    public Map<String, PriorityQueue<String>> getBarcodePromotionsCodeMap() {
        return barcodePromotionsCodeMap;
    }

    public Comparator<String> getComparator() {
        return promotionsComparator;
    }
}
