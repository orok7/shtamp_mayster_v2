package eins.service;

import eins.entity.ProductGroup;

import java.util.List;

public interface ProductGroupService {

    void save(ProductGroup o);

    void remove(Long id);

    ProductGroup findOne(Long id);

    List<ProductGroup> findAll();

}
