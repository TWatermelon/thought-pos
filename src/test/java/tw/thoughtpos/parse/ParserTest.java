package tw.thoughtpos.parse;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

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

    private void assertItem(Item item, String barcode, int amount) {
        assertThat(item.getBarcode(), is(barcode));
        assertThat(item.getAmount(), is(amount));
    }
}
