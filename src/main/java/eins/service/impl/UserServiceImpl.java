package eins.service.impl;

import eins.dao.CompanyUserDao;
import eins.dao.UserDao;
import eins.entity.CompanyUser;
import eins.entity.User;
import eins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao uDAO;
    @Autowired
    private CompanyUserDao cuDAO;

    @Override
    public void save(User user) {
        uDAO.save(user);
    }

    @Override
    public void remove(long id) {
        uDAO.delete(id);
    }

    @Override
    public void save(User user, CompanyUser companyUser) {
        cuDAO.save(companyUser);
        user.setCompany(true);
        user.setCompanyData(companyUser);
        uDAO.save(user);
    }

    @Override
    public User findOne(long id) {
        return uDAO.findOne(id);
    }

    @Override
    public User findOneWithCompanyData(long id) {
        return uDAO.findOneWithCompanyData(id);
    }

    @Override
    public User findByUsername(String username) {
        return uDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String userEmail) {
        return uDAO.findByEmail(userEmail);
    }

    @Override
    public List<User> findAll() {
        return uDAO.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }
}
