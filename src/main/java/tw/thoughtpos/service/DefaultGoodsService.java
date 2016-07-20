package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.repository.GoodsRepository;

@Service
public class DefaultGoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Goods addGoods(String barcode, Goods goods) {
        return goodsRepository.addGoods(barcode, goods);
    }

}
