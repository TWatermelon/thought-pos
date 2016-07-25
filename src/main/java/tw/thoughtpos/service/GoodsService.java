package tw.thoughtpos.service;

import tw.thoughtpos.domain.Goods;

public interface GoodsService {
    Goods addGoods(Goods goods);
    Goods findGoods(String barcode);
}

