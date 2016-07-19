package tw.thoughtpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.repository.GoodsRepository;
import tw.thoughtpos.repository.PromotionsRepository;


@Service
public class DefaultShoppingService implements ShoppingService {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private PromotionsService promotionsService;

    @Autowired
    private PromotionsRepository promotionsRepository;

    public List<ShoppingItem> bindGoods(List<ShoppingItem> shoppingItems) {
        shoppingItems.forEach(this::bindGoods);
        return shoppingItems;
    }

    private void bindGoods(ShoppingItem shoppingItem) {
        shoppingItem.setGoods(goodsRepository.findGoods(shoppingItem.getBarcode()));
    }

    public List<ShoppingItem> prepareBenefits(List<ShoppingItem> shoppingItems) {
        for (ShoppingItem shoppingItem : shoppingItems) {
           Promotions promotions =
                    promotionsRepository.getPromotions(shoppingItem.getBarcode());
            if (promotions != null) {
                shoppingItem.setBenefit(promotions.prepareBenefit(shoppingItem));
            }
        }
        return shoppingItems;
    }

    public Receipt generateReceipt(List<ShoppingItem> shoppingItems) {
        return new Receipt(prepareBenefits(bindGoods(shoppingItems)));
    }
}
