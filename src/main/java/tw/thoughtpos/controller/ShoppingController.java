package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static tw.thoughtpos.utils.ItemParser.parseToItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.service.ShoppingService;

@RestController
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(method = POST, value = "/shoppingItems")
    public ResponseEntity<?> generateReceipt(@RequestBody List<String> inputs) {
        return new ResponseEntity<>(new Receipt(shoppingService
                .prepareBenefits(shoppingService.bindGoods(parseToItem(inputs)))), OK);
    }
}
