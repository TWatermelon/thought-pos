package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.service.PromotionsService;

@RestController
public class PromotionsController {
    @Autowired
    private PromotionsService promotionsService;

    @RequestMapping(method = POST, value = "/promotions")
    public ResponseEntity<?> savePromotions(@RequestBody String promotionsCode,
                                            @RequestBody Promotions promotions) {
        return new ResponseEntity<>(promotionsService.savePromotions(promotionsCode, promotions), CREATED);
    }

    @RequestMapping(method = POST, value = "/promotions")
    public ResponseEntity<?> findPromotions(@RequestBody String promotionsCode) {
        return new ResponseEntity<>(promotionsService.findPromotions(promotionsCode), CREATED);
    }

    @RequestMapping(method = POST, value = "/promotions")
    public ResponseEntity<?> addPromotions(@RequestBody String barcode, @RequestBody String promotionsCode) {
        return new ResponseEntity<>(promotionsService.addPromotions(barcode, promotionsCode), CREATED);
    }

    @RequestMapping(method = POST, value = "/promotions")
    public ResponseEntity<?> getPromotions(@RequestBody String barcode) {
        return new ResponseEntity<>(promotionsService.getPromotions(barcode), CREATED);
    }
}
