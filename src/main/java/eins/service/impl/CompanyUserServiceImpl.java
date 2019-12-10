package eins.service.impl;

import eins.dao.CompanyUserDao;
import eins.entity.CompanyUser;
import eins.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CompanyUserServiceImpl implements CompanyUserService {

    @Autowired
    CompanyUserDao dbDAO;

    @Override
    public void save(CompanyUser o) {
        dbDAO.save(o);
    }

    @Override
    public CompanyUser findOne(Long id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<CompanyUser> findAll() {
        return dbDAO.findAll();
    }
}
