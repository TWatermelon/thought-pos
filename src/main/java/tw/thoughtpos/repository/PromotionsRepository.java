package tw.thoughtpos.repository;

import tw.thoughtpos.promotions.Promotions;

public interface PromotionsRepository {
    Promotions savePromotions(String promotionsCode, Promotions promotions);

    Promotions findPromotions(String promotionsCode);

    Promotions addPromotions(String barcode, String promotionsCode);

    Promotions getPromotions(String barcode);
}
