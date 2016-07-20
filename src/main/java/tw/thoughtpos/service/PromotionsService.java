package tw.thoughtpos.service;

import tw.thoughtpos.promotions.Promotions;

public interface PromotionsService {
    Promotions savePromotions(String promotionsCode, Promotions promotions);

    Promotions findPromotions(String promotionsCode);

    Promotions addPromotions(String barcode, String promotionsCode);

    Promotions getPromotions(String barcode);
}
