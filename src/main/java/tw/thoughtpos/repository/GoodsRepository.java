package tw.thoughtpos.repository;

import tw.thoughtpos.domain.Goods;

public interface GoodsRepository {
    Goods findGoods(String barcode);
    Goods addGoods(String barcode, Goods goods);
}
