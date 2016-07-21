package tw.thoughtpos.register;

import tw.thoughtpos.domain.Receipt;

public final class ReceiptPrinter {
    private static  ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    private ReceiptPrinter() {
    }

    public void print(Receipt receipt, IPrinter printer) {
        CashRegister cashRegister = CashRegister.generateCashRegister();
        printer.print(cashRegister.getReceiptInfo(receipt));
    }

    public static ReceiptPrinter getInstance() {
        return receiptPrinter;
    }
}
