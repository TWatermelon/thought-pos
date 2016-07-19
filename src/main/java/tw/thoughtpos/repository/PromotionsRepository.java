package tw.thoughtpos.repository;

import tw.thoughtpos.promotions.Promotions;

public interface PromotionsRepository {
    void savePromotions(String promotionsCode, Promotions promotions);

    Promotions findPromotions(String promotionsCode);

    void addPromotions(String barcode, String promotionsCode);

    Promotions getPromotions(String barcode);
}
