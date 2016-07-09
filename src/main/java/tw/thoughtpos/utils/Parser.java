package tw.thoughtpos.utils;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.ShoppingItem;

public class Parser {

    private static final String SPLIT_CHAR = "-";
    private static final int DEFAULT_VALUE = 1;
    private static final int SPLIT_LENGHTH = 2;

    public static List<ShoppingItem> parseToItem(List<String> lines) {
        List<ShoppingItem> shoppingItems = lines.stream().map(Parser::splitOutItem).collect(toList());
        return merge(shoppingItems);
    }

    private static List<ShoppingItem> merge(List<ShoppingItem> shoppingItems) {
        Map<String, Integer> itemMap = new LinkedHashMap<>();
        shoppingItems.forEach(item -> {
            String barcode = item.getBarcode();
            int amount = item.getAmount();
            Integer existAmount = itemMap.get(barcode);
            amount = existAmount == null ? amount : amount +  existAmount;
            itemMap.put(barcode, amount);
        });
        return  itemMap.entrySet().stream()
                .map(entry -> new ShoppingItem(entry.getKey(), entry.getValue()))
                .collect(toList());

    }

    private static ShoppingItem splitOutItem(String line) {
        String[] splitResult = line.split(SPLIT_CHAR);
        int amount = splitResult.length == SPLIT_LENGHTH ? parseInt(splitResult[1]) : DEFAULT_VALUE;
        return new ShoppingItem(splitResult[0], amount);
    }
}
