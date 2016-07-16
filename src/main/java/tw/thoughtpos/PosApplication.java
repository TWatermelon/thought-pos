package tw.thoughtpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosApplication {
    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
//        List<String> lines = asList("ITEM000001", "ITEM00003-4");
//        Receipt receipt = ReceiptGenerator.generateReceipt(ItemParser.parseToItem(lines));
//        ReceiptPrinter.getInstance().print(receipt, new ConsolePrinter());
    }
}
