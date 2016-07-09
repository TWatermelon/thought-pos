package tw.thoughtpos.register;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import tw.thoughtpos.domain.Receipt;

public class ReceiptPrinterTest {
    @Test
    public void should_print_receipt_without_promotions_goods() throws Exception {
        PosPrinter printer = mock(PosPrinter.class);
        Receipt receipt = new Receipt();

        ReceiptPrinter.print(receipt, printer);

        verify(printer).print("");
    }
}
