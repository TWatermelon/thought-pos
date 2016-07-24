package tw.thoughtpos.register;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.ShoppingItem;

public class ItemParser {

    private static final String SPLIT_CHAR = "-";
    private static final int DEFAULT_VALUE = 1;
    private static final int SPLIT_LENGTH = 2;

    public List<ShoppingItem> parseToItem(List<String> input) {
        List<ShoppingItem> shoppingItems = input.stream().map(this::splitOutItem).collect(toList());
        return merge(generateBarcodeAmountMap(shoppingItems));
    }

    private List<ShoppingItem> merge(Map<String, Integer> inputMap) {
        return inputMap.entrySet().stream()
                .map(entry -> new ShoppingItem(entry.getKey(), entry.getValue()))
                .collect(toList());

    }

    private Map<String, Integer> generateBarcodeAmountMap(List<ShoppingItem> shoppingItems) {
        Map<String, Integer> itemMap = new LinkedHashMap<>();
        shoppingItems.forEach(item -> {
            String barcode = item.getBarcode();
            int amount = item.getAmount();
            Integer existAmount = itemMap.get(barcode);
            amount = existAmount == null ? amount : amount + existAmount;
            itemMap.put(barcode, amount);
        });
        return itemMap;
    }

    private ShoppingItem splitOutItem(String line) {
        String[] splitResult = line.split(SPLIT_CHAR);
        int amount = splitResult.length == SPLIT_LENGTH ? parseInt(splitResult[1]) : DEFAULT_VALUE;
        return new ShoppingItem(splitResult[0], amount);
    }
}
