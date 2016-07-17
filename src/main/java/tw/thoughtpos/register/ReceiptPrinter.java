package tw.thoughtpos.register;

import tw.thoughtpos.domain.Receipt;

public final class ReceiptPrinter {
    private static  ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    private ReceiptPrinter() {
    }

    public void print(Receipt receipt, PosPrinter printer) {
        CashRegister cashRegister = CashRegister.generateCashRegister(receipt);
        printer.print(cashRegister.getReceiptInfo());
    }

    public static ReceiptPrinter getInstance() {
        return receiptPrinter;
    }
}
