package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.service.GoodsService;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = POST, value = "/goods")
    public ResponseEntity<?> addGoods(@RequestBody Goods goods) {
        return new ResponseEntity<>(goodsService.addGoods(goods), CREATED);
    }

    @RequestMapping(method = GET, value = "/goods/{barcode}")
    public ResponseEntity<?> findGoods(@PathVariable String barcode) {
        return new ResponseEntity<>(goodsService.findGoods(barcode), OK);
    }
}
