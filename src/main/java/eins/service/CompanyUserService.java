package eins.service;

import eins.entity.CompanyUser;

import java.util.List;

public interface CompanyUserService {

    void save(CompanyUser o);

    CompanyUser findOne(Long id);

    List<CompanyUser> findAll();

}
