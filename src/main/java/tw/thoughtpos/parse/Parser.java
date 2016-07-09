package tw.thoughtpos.parse;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.thoughtpos.domain.ShoppingItem;

public class Parser {

    public List<ShoppingItem> parse(List<String> lines) {
        return lines.stream().map(this::getItem).collect(toList());
    }

    private ShoppingItem getItem(String line) {
        return new ShoppingItem(this.getBarcode(line), this.getAmount(line));
    }

    private String getBarcode(String line) {
        return line.contains("-") ? line.split("-")[0] : line;
    }

    private int getAmount(String line) {
        return line.contains("-") ? Integer.parseInt(line.split("-")[1]) : 1;
    }

    public Map<String, Integer> get_barcode_amount_map(List<String> lines) {
        Map<String, Integer> result = new HashMap<>();
        for (String line : lines) {
            if (result.containsKey(this.getBarcode(line))) {
                result.put(this.getBarcode(line), this.getAmount(line) + result.get(this.getBarcode(line)));
            } else {
                result.put(this.getBarcode(line), this.getAmount(line));
            }
        }
        return result;
    }
}
