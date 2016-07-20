package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.service.DefaultGoodsService;

@RestController
public class GoodsController {
    @Autowired
    private DefaultGoodsService defaultGoodsService;

    @RequestMapping(method = POST, value = "/goods")
    public ResponseEntity<?> addGoods(@RequestBody String barcode, @RequestBody Goods goods) {
        return new ResponseEntity<>(defaultGoodsService.addGoods(barcode, goods), CREATED);
    }
}
