package tw.thoughtpos.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.thoughtpos.promotions.Promotions;
import tw.thoughtpos.service.PromotionsService;

@RestController
public class PromotionsController {
    @Autowired
    private PromotionsService promotionsService;

//    @RequestMapping(method = POST, value = "/Promotions")
    public ResponseEntity<?> savePromotions(@RequestParam String promotionsCode,
                                            @RequestBody Promotions promotions) {
        return new ResponseEntity<>(promotionsService.savePromotions(promotionsCode, promotions), CREATED);
    }

//    @RequestMapping(method = GET, value = "/findPromotions")
    public ResponseEntity<?> findPromotions(@RequestParam String promotionsCode) {
        return new ResponseEntity<>(promotionsService.findPromotions(promotionsCode), OK);
    }

//    @RequestMapping(method = POST, value = "/addPromotions")
    public ResponseEntity<?> addPromotions(@RequestParam String barcode, @RequestParam String promotionsCode) {
        return new ResponseEntity<>(promotionsService.addPromotions(barcode, promotionsCode), CREATED);
    }

//    @RequestMapping(method = GET, value = "/getPromotions")
    public ResponseEntity<?> getPromotions(@RequestParam String barcode) {
        return new ResponseEntity<>(promotionsService.getPromotions(barcode), OK);
    }
}
