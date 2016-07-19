package tw.thoughtpos.service;

import tw.thoughtpos.promotions.Promotions;

public interface PromotionsService {
    Promotions findPromotions(String barcode);
}
