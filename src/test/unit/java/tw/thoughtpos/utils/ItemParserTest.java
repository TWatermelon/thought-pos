package tw.thoughtpos.utils;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.thoughtpos.domain.ShoppingItem;
import tw.thoughtpos.register.ItemParser;

public class ItemParserTest {

    private List<String> input;

    private ItemParser itemParser;

    @Before
    public void setUp() {
        input = asList("ITEM00001", "ITEM00002-5", "ITEM00001", "ITEM00003");
        itemParser = new ItemParser();
    }


    @Test
    public void should_return_items_given_lines() {
        List<ShoppingItem> items = itemParser.parseToItem(input);
        assertThat(items.size(), is(3));
        assertItem(items.get(0), "ITEM00001", 2);
        assertItem(items.get(1), "ITEM00002", 5);
        assertItem(items.get(2), "ITEM00003", 1);
    }


    private void assertItem(ShoppingItem item, String expectedBarcode, int expectedAmount) {
        assertThat(item.getBarcode(), is(expectedBarcode));
        assertThat(item.getAmount(), is(expectedAmount));
    }

}
