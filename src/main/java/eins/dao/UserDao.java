package eins.dao;

import eins.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Integer> {

    @Query("select u from User u left outer join fetch u.companyData where u.id=:id")
    User findOneWithCompanyData(@Param("id") int id);

    User findByUsername(String username);

    User findByEmail(String userEmail);

}
