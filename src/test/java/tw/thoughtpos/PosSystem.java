package tw.thoughtpos;

import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tw.thoughtpos.controller.ShoppingController;
import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.register.ConsolePrinter;
import tw.thoughtpos.register.ReceiptPrinter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PosApplication.class)
@WebAppConfiguration
public class PosSystem {
    @Autowired
    private ShoppingController shoppingController;

    @Test
    public void output_result() {
        List<String> inputs = asList("ITEM000001",
                "ITEM000002-8", "ITEM000003-5", "ITEM000004");
        ReceiptPrinter.getInstance().print((Receipt) shoppingController.generateReceipt(inputs).getBody(),
                new ConsolePrinter());
    }
}
