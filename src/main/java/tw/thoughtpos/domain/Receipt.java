package tw.thoughtpos.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.register.Record;

public class Receipt {
    private List<ShoppingItem> shoppingItems;
    private Map<String, List<Record>> mapper = new LinkedHashMap<>();
    private double orderSaveOfFullFree = 0d;
    private double totalMoneyOfFullFreeGoods = 0d;

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setMapper(Map<String, List<Record>> mapper) {
        this.mapper = mapper;
    }

    public Map<String, List<Record>> getMapper() {
        return mapper;
    }

    public double getOrderSaveOfFullFree() {
        return orderSaveOfFullFree;
    }

    public double getTotalMoneyOfFullFreeGoods() {
        return totalMoneyOfFullFreeGoods;
    }

    public void setOrderSaveOfFullFree(double orderSaveOfFullFree) {
        this.orderSaveOfFullFree = orderSaveOfFullFree;
    }

    public void setTotalMoneyOfFullFreeGoods(double totalMoneyOfFullFreeGoods) {
        this.totalMoneyOfFullFreeGoods = totalMoneyOfFullFreeGoods;
    }
}
