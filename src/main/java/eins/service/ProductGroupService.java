package eins.service;

import eins.entity.ProductGroup;

import java.util.List;

public interface ProductGroupService {

    void save(ProductGroup o);

    void remove(long id);

    ProductGroup findOne(long id);

    List<ProductGroup> findAll();

}
