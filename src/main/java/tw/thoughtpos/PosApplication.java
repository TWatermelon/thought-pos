package tw.thoughtpos;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tw.thoughtpos.register.ConsolePrinter;
import tw.thoughtpos.register.ReceiptPrinter;
import tw.thoughtpos.utils.HttpUtil;

@SpringBootApplication
public class PosApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PosApplication.class, args);
        List<String> inputs = asList("ITEM000001",
                "ITEM000002-8", "ITEM000003-5", "ITEM000004", "ITEM000005");
        ReceiptPrinter.getInstance().print(HttpUtil.post("http://localhost:8081/pos/shoppingItems", inputs),
                new ConsolePrinter());
    }
}
