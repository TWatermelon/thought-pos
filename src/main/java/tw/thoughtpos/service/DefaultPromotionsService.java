package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.PromotionsRepository;

@Service
public class DefaultPromotionsService implements PromotionsService {
    @Autowired
    private PromotionsRepository promotionsRepository;

    public Promotions savePromotions(String promotionsCode, Promotions promotions) {
        return promotionsRepository.savePromotions(promotionsCode, promotions);
    }

    public Promotions findPromotions(String promotionsCode) {
        return promotionsRepository.findPromotions(promotionsCode);
    }

    public Promotions addPromotions(String barcode, String promotionsCode) {
        return promotionsRepository.addPromotions(barcode, promotionsCode);
    }

    public Promotions getPromotions(String barcode) {
        return promotionsRepository.getPromotions(barcode);
    }
}
