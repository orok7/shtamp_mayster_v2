package eins.service;

import eins.entity.ProductToBuy;

import java.util.List;

public interface ProductToBuyService {

    void save(ProductToBuy o);

    ProductToBuy findOne(Long id);

    List<ProductToBuy> findAll();

}
