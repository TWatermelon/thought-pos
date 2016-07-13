package tw.thoughtpos.register;

import static tw.thoughtpos.utils.ConstantUtil.COMMA;
import static tw.thoughtpos.utils.ConstantUtil.MONEY_UNIT;
import static tw.thoughtpos.utils.ConstantUtil.NEW_LINE_CHAR;
import static tw.thoughtpos.utils.ConstantUtil.SEPARATOR_LINE;
import static tw.thoughtpos.utils.ConstantUtil.TITLE;
import static tw.thoughtpos.utils.FormatUtil.format;

import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;

public final class ReceiptPrinter {
    private static  ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    private ReceiptPrinter() {
    }

    public void print(Receipt receipt, PosPrinter printer) {
        double totalPrice = 0d;
        double totalSave = 0d;
        StringBuilder builder = new StringBuilder();
        builder.append(TITLE);
        for (ShoppingItem shoppingItem : receipt.getShoppingItems()) {
            builder.append(getItemDetail(shoppingItem));
            totalPrice += shoppingItem.getSubtotalPrice();
            totalSave += shoppingItem.getAllowance();
        }
        totalPrice -= receipt.getOrderSaveOfFullMinus();
        totalSave += receipt.getOrderSaveOfFullMinus();
        builder.append(getAllPromotionsInfo(receipt))
                .append(getTotalFullMinusInfo(receipt))
                .append(SEPARATOR_LINE)
                .append("总计：").append(format(totalPrice)).append(MONEY_UNIT).append(NEW_LINE_CHAR)
                .append(getTotalSaveInfo(totalSave));
        printer.print(builder.toString());
    }

    private String getAllPromotionsInfo(Receipt receipt) {
        StringBuilder builder = new StringBuilder();
        Map<String, List<PromotionsRecord>> mapper = receipt.getMapper();
            for (Map.Entry<String, List<PromotionsRecord>> entry : mapper.entrySet()) {
                builder.append(getPromotionsInfo(entry.getKey(), entry.getValue()));
            }
            return builder.toString();
    }

    private String getTotalFullMinusInfo(Receipt receipt) {
        StringBuilder builder = new StringBuilder();
        double totalMoneyOfGoodsWithFullMinus = receipt.getTotalMoneyOfFullMinusGoods();
        if (totalMoneyOfGoodsWithFullMinus > 0) {
            builder.append("参与优惠总价：")
                    .append(format(totalMoneyOfGoodsWithFullMinus)).append(MONEY_UNIT).append(COMMA)
                    .append("优惠：").append(format(receipt.getOrderSaveOfFullMinus())).append(MONEY_UNIT)
                    .append(NEW_LINE_CHAR);
        }
        return builder.toString();
    }

    private String getPromotionsInfo(String type, List<PromotionsRecord> records) {
        StringBuilder builder = new StringBuilder();
        if (records.size() > 0) {
            builder.append(SEPARATOR_LINE);
            builder.append(type + "：").append(NEW_LINE_CHAR);
            records.forEach(record -> builder.append(record.showRecord()).append(NEW_LINE_CHAR));
        }
        return builder.toString();
    }

    private static String getTotalSaveInfo(double totalSave) {
        StringBuilder  builder = new StringBuilder();
            return totalSave > 0 ? builder.append("节省：")
                    .append(format(totalSave))
                    .append(MONEY_UNIT).append(NEW_LINE_CHAR).toString()
                    : builder.toString();
    }

    private String getItemDetail(ShoppingItem shoppingItem) {
        return shoppingItem.toString();
    }

    public static ReceiptPrinter getInstance() {
        return receiptPrinter;
    }
}
