package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.PromotionsRepository;

@Service
public class DefaultPromotionsService {
    @Autowired
    private PromotionsRepository promotionsRepository;

    public void savePromotions(String promotionsCode, Promotions promotions) {
        promotionsRepository.savePromotions(promotionsCode, promotions);
    }

    public Promotions findPromotions(String promotionsCode) {
        return promotionsRepository.findPromotions(promotionsCode);
    }

    public void addPromotions(String barcode, String promotionsCode) {
        promotionsRepository.addPromotions(barcode, promotionsCode);
    }

    public Promotions getPromotions(String barcode) {
        return promotionsRepository.getPromotions(barcode);
    }
}
