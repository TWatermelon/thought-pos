package tw.thoughtpos;

import static java.util.Arrays.asList;

import java.util.List;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.register.ConsolePrinter;
import tw.thoughtpos.register.ReceiptGenerator;
import tw.thoughtpos.register.ReceiptPrinter;
import tw.thoughtpos.utils.ItemParser;

public class PosApplication {
    public static void main(String[] args) {
        List<String> lines = asList("ITEM000001", "ITEM00003-4");
        Receipt receipt = ReceiptGenerator.generateReceipt(ItemParser.parseToItem(lines));
        ReceiptPrinter.getInstance().print(receipt, new ConsolePrinter());
    }
}
