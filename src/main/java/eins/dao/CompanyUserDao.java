package eins.dao;

import eins.entity.CompanyUser;
import eins.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserDao extends JpaRepository<CompanyUser, Long> {

}
