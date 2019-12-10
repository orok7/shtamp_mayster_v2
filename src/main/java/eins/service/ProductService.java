package eins.service;

import eins.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {

    void save(Product o);

    void remove(Long id);

    Product findOne(Long id);

    List<Product> findAll();

    List<Product> findAllWithGroups();

    Product findOneWithGroup(Long id);

    List<Product> findAllByProductGroupName(String name);

    List<Product> findAllBySearch(String searchThis);

}
