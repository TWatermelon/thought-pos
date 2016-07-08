package tw.thoughtpos.parse;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Parser {
    public List<Item> parse(List<String> lines) {
        return lines.stream().map(this::getItem).collect(toList());
    }

    private Item getItem(String line) {
        return new Item(this.getBarcode(line), this.getAmount(line));
    }

    private String getBarcode(String line) {
        return line.contains("-") ? line.split("-")[0] : line;
    }

    private int getAmount(String line) {
        return line.contains("-") ? Integer.parseInt(line.split("-")[1]) : 1;
    }
}
