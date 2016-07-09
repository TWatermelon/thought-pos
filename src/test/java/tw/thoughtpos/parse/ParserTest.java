package tw.thoughtpos.parse;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ParserTest {

    @Test
    public void should_return_items_given_lines() {
        List<String> lines = asList("ITEM00001", "ITEM00002-5", "ITEM00001", "ITEM00003");
        Parser parser = new Parser();
        List<Item> items = parser.parse(lines);
        assertThat(items.size(), is(4));
        assertItem(items.get(0), "ITEM00001", 1);
        assertItem(items.get(1), "ITEM00002", 5);
        assertItem(items.get(2), "ITEM00001", 1);
        assertItem(items.get(3), "ITEM00003", 1);
    }

    private void assertItem(Item item, String expectedBarcode, int expectedAmount) {
        assertThat(item.getBarcode(), is(expectedBarcode));
        assertThat(item.getAmount(), is(expectedAmount));
    }

    @Test
    public void should_return_barcode_amount_map_given_lines() {
        List<String> lines = asList("ITEM00001", "ITEM00002-5", "ITEM00001", "ITEM00003");
        Parser parser = new Parser();
        Map<String, Integer> barcodeAmountMap = parser.get_barcode_amount_map(lines);
        assertThat(barcodeAmountMap.size(), is(3));
        assertThat(barcodeAmountMap.get("ITEM00001"), is(2));
        assertThat(barcodeAmountMap.get("ITEM00002"), is(5));
        assertThat(barcodeAmountMap.get("ITEM00003"), is(1));

    }
}
