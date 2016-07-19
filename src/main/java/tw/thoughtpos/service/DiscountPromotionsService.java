package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.promotions.DiscountPromotions;
import tw.thoughtpos.repository.DiscountPromotionRepository;

@Service
public class DiscountPromotionsService implements PromotionsService {

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    @Override
    public DiscountPromotions findPromotions(String barcode) {
        return discountPromotionRepository.findOne(barcode);
    }
}
