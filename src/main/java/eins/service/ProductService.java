package eins.service;

import eins.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {

    void save(Product o);

    void remove(long id);

    Product findOne(long id);

    List<Product> findAll();

    List<Product> findAllWithGroups();

    Product findOneWithGroup(long id);

    List<Product> findAllByProductGroupName(String name);

    List<Product> findAllBySearch(String searchThis);

}
