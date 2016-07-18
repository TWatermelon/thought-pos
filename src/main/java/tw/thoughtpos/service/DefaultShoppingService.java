package tw.thoughtpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.repository.GoodsRepository;

@Service
public class DefaultShoppingService implements ShoppingService {
    @Autowired
    private GoodsRepository goodsRepository;

    public List<ShoppingItem> bindGoods(List<ShoppingItem> shoppingItems) {
        shoppingItems.forEach(this::bindGoods);
        return  shoppingItems;
    }

    private void bindGoods(ShoppingItem shoppingItem) {
        shoppingItem.setGoods(goodsRepository.findGoods(shoppingItem.getBarcode()));
    }

    public List<ShoppingItem> prepareBenefits(List<ShoppingItem> shoppingItems) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (shoppingItem.getGoods().getPromotions() != null) {
                shoppingItem.setBenefit(
                        shoppingItem.getGoods().getPromotions().calculate(shoppingItem));
            }
        }
        return shoppingItems;
    }

    public Receipt generateReceipt(List<ShoppingItem> shoppingItems) {
        return new Receipt(prepareBenefits(bindGoods(shoppingItems)));
    }
}
