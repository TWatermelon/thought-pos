package tw.thoughtpos.register;

import static java.util.stream.Collectors.joining;
import static tw.thoughtpos.utils.ConstantUtil.COMMA;
import static tw.thoughtpos.utils.ConstantUtil.MONEY_UNIT;
import static tw.thoughtpos.utils.ConstantUtil.NEW_LINE_CHAR;
import static tw.thoughtpos.utils.ConstantUtil.SEPARATOR_LINE;
import static tw.thoughtpos.utils.ConstantUtil.TITLE;
import static tw.thoughtpos.utils.FormatUtil.format;

import java.util.List;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;

public final class CashRegister {
    private Receipt receipt;

    private CashRegister() {
    }

    public static CashRegister generateCashRegister(Receipt receipt) {
        CashRegister cashRegister = new CashRegister();
        cashRegister.setReceipt(receipt);
        return cashRegister;
    }

    public String getReceiptInfo() {
        return new StringBuilder().append(TITLE)
                .append(getAllItemsDetail())
                .append(getAllPromotionsInfo())
                .append(SEPARATOR_LINE)
                .append(getTotalPriceInfo(getTotalPrice()))
                .append(getTotalSaveInfo(getTotalSave()))
                .toString();
    }

    private String getAllItemsDetail() {
        return receipt.getShoppingItems().stream().map(shoppingItem ->
                getItemDetail(shoppingItem)).collect(joining());
    }

    private String getAllPromotionsInfo() {
        return receipt.getMapper().entrySet().stream()
                .map(entry -> getPromotionsInfo(entry.getKey(), entry.getValue())).collect(joining());
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

    private double getTotalPrice() {
        double totalPrice = receipt.getShoppingItems().stream()
                .mapToDouble(shoppingItem -> shoppingItem.getSubtotalPrice()).sum();
        return totalPrice - receipt.getOrderSaveOfFullMinus();
    }

    private double getTotalSave() {
        double totalSave = receipt.getShoppingItems().stream()
                .mapToDouble(shoppingItem -> shoppingItem.getAllowance()).sum();
        return totalSave + receipt.getOrderSaveOfFullMinus();
    }

    private String getTotalFullMinusInfo() {
        double totalMoneyOfGoodsWithFullMinus = receipt.getTotalMoneyOfFullMinusGoods();
        return totalMoneyOfGoodsWithFullMinus > 0 ?
                generateTotalFullMinusInfo(totalMoneyOfGoodsWithFullMinus) : "";
    }

    private String generateTotalFullMinusInfo(double totalMoneyOfGoodsWithFullMinus) {
        return new StringBuilder().append("参与优惠总价：")
                .append(format(totalMoneyOfGoodsWithFullMinus)).append(MONEY_UNIT).append(COMMA)
                .append("优惠：").append(format(receipt.getOrderSaveOfFullMinus())).append(MONEY_UNIT)
                .append(NEW_LINE_CHAR).toString();
    }

    private String getPromotionsInfo(String type, List<PromotionsRecord> records) {
        StringBuilder builder = new StringBuilder();
        if (records.size() > 0) {
            builder.append(SEPARATOR_LINE)
                    .append(type + "：").append(NEW_LINE_CHAR);
            records.forEach(record -> builder.append(record.showRecord()).append(NEW_LINE_CHAR));
            if (type.equals("不参与优惠商品")) {
                builder.append(getTotalFullMinusInfo());
            }
        }
        return builder.toString();
    }

    private String getItemDetail(ShoppingItem shoppingItem) {
        return shoppingItem.toString();
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
