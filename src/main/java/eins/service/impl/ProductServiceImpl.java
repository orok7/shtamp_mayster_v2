package eins.service.impl;

import eins.dao.ProductDao;
import eins.entity.Product;
import eins.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao dbDAO;

    @Override
    public void save(Product o) {
        dbDAO.save(o);
    }

    @Override
    public void remove(int id) {
        dbDAO.delete(id);
    }

    @Override
    public Product findOne(int id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return dbDAO.findAll();
    }

    @Override
    public List<Product> findAllWithGroups() {
        return dbDAO.findAllWithGroups();
    }

    @Override
    public Product findOneWithGroup(int id) {
        return dbDAO.findOneWithGroup(id);
    }

    @Override
    public List<Product> findAllByProductGroupName(String name) {
        return dbDAO.findAllByGroupName(name);
    }

    @Override
    public List<Product> findAllBySearch(String searchThis) {
        return dbDAO.findAllBySearch(searchThis);
    }
}