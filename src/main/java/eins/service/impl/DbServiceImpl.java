package eins.service.impl;

import eins.dao.*;
import eins.entity.*;
import eins.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class DbServiceImpl implements DbService {

    @Override
    public void save(Object o, Class<?> clazz) {
        initMyDAOMap();
        dbDAO.get(clazz).save(o);
    }

    @Override
    public Object findOne(Long id, Class<?> clazz) {
        initMyDAOMap();
        return dbDAO.get(clazz).findOne(id);
    }

//    @Override
//    public void save(Map<String, String> map, Class<?> clazz) throws Exception {
//        initMyDAOMap();
//        dbDAO.get(clazz).save(map);
//    }

    @Override
    public List<Object> findAll(Class<?> clazz) {
        initMyDAOMap();
        return dbDAO.get(clazz).findAll();
    }



    ////////////////////////////////////////////////////////////////////////


    
    @Autowired private CompanyUserDao companyUserDao;
    @Autowired private InvoiceDao invoiceDao;
    @Autowired private ProductGroupDao productGroupDao;
    @Autowired private ProductDao productDao;
    @Autowired private ProductToBuyDao productToBuyDao;
    @Autowired private ReviewDao reviewDao;
    @Autowired private UserDao uDAO;

    private Map<Class<?>, JpaRepository> dbDAO = new HashMap<>();

    private void initMyDAOMap(){
        dbDAO.put(CompanyUser.class, companyUserDao);
        dbDAO.put(Invoice.class, invoiceDao);
        dbDAO.put(ProductGroup.class, productGroupDao);
        dbDAO.put(Product.class, productDao);
        dbDAO.put(ProductToBuy.class, productToBuyDao);
        dbDAO.put(Review.class, reviewDao);
        dbDAO.put(User.class, uDAO);
    }
}
