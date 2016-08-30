package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.service.GoodsService;

@RestController
@EnableAutoConfiguration
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = POST, value = "/goods")
    public ResponseEntity<?> addGoods(@RequestBody Goods goods) {
        return new ResponseEntity<>(goodsService.addGoods(goods), CREATED);
    }

    @RequestMapping(method = GET, value = "/goods")
    public ResponseEntity<?> findGoods(@RequestParam String barcode) {
        return new ResponseEntity<>(goodsService.findGoods(barcode), OK);
    }
}
