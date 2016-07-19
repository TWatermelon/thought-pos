package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.promotions.AmountFreePromotions;
import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.AmountFreePromotionsRepository;
import tw.thoughtpos.repository.DiscountPromotionRepository;

@Service
public class DefaultPromotionsService implements PromotionsService {

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    @Autowired
    private AmountFreePromotionsRepository amountFreePromotionsRepository;

    @Override
    public Promotions findPromotions(String barcode) {
        AmountFreePromotions amountFreePromotions = amountFreePromotionsRepository.findOne(barcode);
        DiscountPromotions discountPromotions = discountPromotionRepository.findOne(barcode);
        if (discountPromotions != null) {
            return discountPromotions;
        }
        return amountFreePromotions != null && discountPromotions == null ? amountFreePromotions
                : null;
    }
}
