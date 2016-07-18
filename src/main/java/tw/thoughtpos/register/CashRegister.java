package tw.thoughtpos.register;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static tw.thoughtpos.utils.ConstantUtil.COMMA;
import static tw.thoughtpos.utils.ConstantUtil.MONEY_UNIT;
import static tw.thoughtpos.utils.ConstantUtil.NEW_LINE_CHAR;
import static tw.thoughtpos.utils.ConstantUtil.SEPARATOR_LINE;
import static tw.thoughtpos.utils.ConstantUtil.TITLE;
import static tw.thoughtpos.utils.FormatUtil.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.promotions.Benefit;

public final class CashRegister {
    private Receipt receipt;

    public  static CashRegister generateCashRegister(Receipt receipt) {
        CashRegister cashRegister = new CashRegister();
        cashRegister.setReceipt(receipt);
        return cashRegister;
    }

    public String getReceiptInfo() {
        return new StringBuilder().append(TITLE)
                .append(getAllItemsDetail())
                .append(getAllPromotionsInfo())
                .append(SEPARATOR_LINE)
                .append(getTotalPriceInfo(receipt.getTotalPrice() - receipt.getTotalSave()))
                .append(getTotalSaveInfo(receipt.getTotalSave()))
                .toString();
    }

    private String getAllItemsDetail() {
        return receipt.getShoppingItems().stream().map(shoppingItem ->
                getItemDetail(shoppingItem)).collect(joining());
    }

    private String getAllPromotionsInfo() {
        return mergeShoppingItemsWithPromotions(getShoppingItemsWithPromotions()).entrySet().stream()
                .map(entry -> getPromotionsInfo(entry.getKey(), entry.getValue())).collect(joining());
    }

    private String getPromotionsInfo(String name, List<ShoppingItem> shoppingItems) {
        StringBuilder builder = new StringBuilder();
        if (name.equals("买二赠一商品")) {
            builder.append(SEPARATOR_LINE)
                    .append(name + "：").append(NEW_LINE_CHAR);
            shoppingItems.forEach(shoppingItem -> {
                Goods goods = shoppingItem.getGoods();
                Benefit benefit = shoppingItem.getBenefit();
                builder.append("名称：").append(goods.getName())
                        .append(COMMA).append("数量：").append(benefit.getSaveAmount())
                        .append(goods.getUnit()).append(NEW_LINE_CHAR);
            });
        }
        return builder.toString();
    }

    private String getTotalPriceInfo(double totalPrice) {
        return new StringBuilder().append("总计：").append(format(totalPrice))
                .append(MONEY_UNIT).append(NEW_LINE_CHAR).toString();
    }

    private String getTotalSaveInfo(double totalSave) {
        StringBuilder builder = new StringBuilder();
        return totalSave > 0 ? builder.append("节省：")
                .append(format(totalSave))
                .append(MONEY_UNIT).append(NEW_LINE_CHAR).toString()
                : builder.toString();
    }

    private  Map<String, List<ShoppingItem>> mergeShoppingItemsWithPromotions(
            List<ShoppingItem> shoppingItems) {
        Map<String, List<ShoppingItem>> result = new HashMap<>();
        shoppingItems.forEach(item -> {
            String name = item.getBenefit().getName();
            if (result.containsKey(name)) {
                List<ShoppingItem> items = result.get(name);
                items.add(item);
                result.put(name, items);
            } else {
                List<ShoppingItem> items = new ArrayList<>();
                items.add(item);
                result.put(name, items);
            }
        });
        return result;

    }

    private List<ShoppingItem> getShoppingItemsWithPromotions() {
        return receipt.getShoppingItems().stream()
                .filter(shoppingItem -> shoppingItem.getBenefit().getName() != null)
                .collect(toList());
    }

    private String getItemDetail(ShoppingItem shoppingItem) {
        return shoppingItem.toString();
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
