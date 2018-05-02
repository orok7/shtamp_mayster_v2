package eins.service;

import eins.entity.ProductGroup;

import java.util.List;

public interface ProductGroupService {

    void save(ProductGroup o);

    void remove(int id);

    ProductGroup findOne(int id);

    List<ProductGroup> findAll();

}
