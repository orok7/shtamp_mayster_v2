package eins.service.impl;

import eins.dao.ProductGroupDao;
import eins.entity.ProductGroup;
import eins.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ProductGroupServiceImpl implements ProductGroupService {

    @Autowired
    ProductGroupDao dbDAO;

    @Override
    public void save(ProductGroup o) {
        dbDAO.save(o);
    }

    @Override
    public void remove(long id) {
        dbDAO.delete(id);
    }

    @Override
    public ProductGroup findOne(long id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<ProductGroup> findAll() {
        return dbDAO.findAllOrderByName();
    }
}