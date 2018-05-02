package eins.dao;

import eins.entity.ProductToBuy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductToBuyDao extends JpaRepository<ProductToBuy,Integer> {


}
