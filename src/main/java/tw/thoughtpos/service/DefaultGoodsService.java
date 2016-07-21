package tw.thoughtpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.thoughtpos.domain.Goods;
import tw.thoughtpos.repository.GoodsRepository;

@Service
public class DefaultGoodsService implements GoodService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Goods addGoods(Goods goods) {
        return goodsRepository.addGoods(goods.getBarcode(), goods);
    }

    @Override
    public Goods findGoods(String barcode) {
        return goodsRepository.findGoods(barcode);
    }

}
