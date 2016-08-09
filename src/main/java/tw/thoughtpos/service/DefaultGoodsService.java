package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.repository.GoodsRepository;

@Service
public class DefaultGoodsService implements GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Goods addGoods(Goods goods) {
        return goodsRepository.addGoods(goods);
    }

    @Override
    public Goods findGoods(String barcode) {
        return goodsRepository.findGoods(barcode);
    }

}
