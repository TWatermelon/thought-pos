package tw.thoughtpos.register;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;

public class ReceiptPrinter {

    private static final String TITLE = "***<没钱赚商店>购物清单***" + "\n";
    private static final String SEPARATOR_LINE = "----------" + "\n";
    private static final String COMMA = ",";
    private static final String FORMAT_STR = "0.00";
    private static final String MONEY_UNIT = "（元）";

    public static void print(Receipt receipt, PosPrinter printer) {
        double totalPrice = 0d;
        double totalSave = 0d;
        StringBuilder builder = new StringBuilder();
        builder.append(TITLE);
        for (ShoppingItem shoppingItem : receipt.getShoppingItems()) {
            builder.append(getItemDetail(shoppingItem));
            totalPrice += getSubtotalPrice(shoppingItem);
            totalSave += getAllowance(shoppingItem);
        }
        builder.append(getDiscountInfo(receipt))
                .append(SEPARATOR_LINE)
                .append("总计：").append(format(totalPrice)).append(MONEY_UNIT).append("\n")
                .append(getTotalSaveInfo(totalSave));
        printer.print(builder.toString());
    }

    private static String getDiscountInfo(Receipt receipt) {
        StringBuilder builder = new StringBuilder();
        Map<String, List<Record>> mapper = receipt.getMapper();
            for (Map.Entry<String, List<Record>> entry : mapper.entrySet()) {
                builder.append(getAllDiscountInfo(entry.getKey(), entry.getValue()));
            }
            return builder.toString();
    }

    private static String getAllDiscountInfo(String type, List<Record> records) {
        StringBuilder builder = new StringBuilder();
        if (records.size() > 0) {
            builder.append(SEPARATOR_LINE);
            builder.append(type + "：").append("\n");
            records.forEach(record -> builder.append(record.showRecord()).append("\n"));
        } else {
            builder.append("");
        }
        return builder.toString();
    }

    private static String getTotalSaveInfo(double totalSave) {
        StringBuilder  builder = new StringBuilder();
            return totalSave > 0 ? builder.append("节省：")
                    .append(format(totalSave))
                    .append(MONEY_UNIT).append("\n").toString()
                    : builder.append("").toString();

    }

    private static String getItemDetail(ShoppingItem shoppingItem) {
        return new StringBuilder().append(getName(shoppingItem))
                .append(getAmount(shoppingItem))
                .append(getUnit(shoppingItem))
                .append(getPrice(shoppingItem))
                .append(getSubtotalInfo(shoppingItem))
                .append(getAllowanceInfo(shoppingItem))
                .append("\n").toString();
    }

    private static double getSubtotalPrice(ShoppingItem shoppingItem) {
        return shoppingItem.getSubtotal() - getAllowance(shoppingItem);
    }

    private static String getAllowanceInfo(ShoppingItem shoppingItem) {
        StringBuilder builder = new StringBuilder();
        double allowance = getAllowance(shoppingItem);
        return allowance > 0 ? builder.append(COMMA + "优惠")
                .append(format(allowance)).append(MONEY_UNIT).toString() : builder.append("").toString();
    }

    private static double getAllowance(ShoppingItem shoppingItem) {
        return shoppingItem.getBenefit().getAllowance();
    }

    private static String getSubtotalInfo(ShoppingItem shoppingItem) {
        return new StringBuilder().append("小计:")
                .append(format(getSubtotalPrice(shoppingItem)))
                .append(MONEY_UNIT).toString();
    }

    private static StringBuilder getAmount(ShoppingItem shoppingItem) {
        return new StringBuilder().append("数量：").append(shoppingItem.getAmount());
    }

    private static String getUnit(ShoppingItem shoppingItem) {
        return new StringBuilder()
                .append(shoppingItem.getGoods().getUnit())
                .append(COMMA).toString();
    }

    private static String getPrice(ShoppingItem shoppingItem) {
        return new StringBuilder().append("单价：")
                .append(format(shoppingItem.getGoods().getPrice()))
                .append(MONEY_UNIT)
                .append(COMMA).toString();
    }

    private static String format(double price) {
        return new DecimalFormat(FORMAT_STR).format(price);
    }

    private static String getName(ShoppingItem shoppingItem) {

        return new StringBuilder().append("名称：")
                .append(shoppingItem.getGoods().getName())
                .append(COMMA).toString();
    }
}
