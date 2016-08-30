package tw.thoughtpos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.thoughtpos.domain.Goods;
@Repository
public interface GoodsRepository extends CrudRepository<Goods, String> {
}
