package tw.thoughtpos.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.register.PromotionsRecord;

public class Receipt {
    private List<ShoppingItem> shoppingItems = new LinkedList<>();
    private Map<String, List<PromotionsRecord>> mapper = new LinkedHashMap<>();
    private double orderSaveOfFullMinus = 0d;
    private double totalMoneyOfFullMinusGoods = 0d;

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setMapper(Map<String, List<PromotionsRecord>> mapper) {
        this.mapper = mapper;
    }

    public Map<String, List<PromotionsRecord>> getMapper() {
        return mapper;
    }

    public double getOrderSaveOfFullMinus() {
        return orderSaveOfFullMinus;
    }

    public double getTotalMoneyOfFullMinusGoods() {
        return totalMoneyOfFullMinusGoods;
    }

    public void setOrderSaveOfFullMinus(double orderSaveOfFullMinus) {
        this.orderSaveOfFullMinus = orderSaveOfFullMinus;
    }

    public void setTotalMoneyOfFullMinusGoods(double totalMoneyOfFullMinusGoods) {
        this.totalMoneyOfFullMinusGoods = totalMoneyOfFullMinusGoods;
    }
}
