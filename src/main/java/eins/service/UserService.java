package eins.service;

import eins.entity.CompanyUser;
import eins.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void remove(Long id);

    void save(User user, CompanyUser companyUser);

    User findOne(Long id);

    User findOneWithCompanyData(Long id);

    User findByUsername(String username);

    User findByEmail(String userEmail);

    List<User> findAll();
}
