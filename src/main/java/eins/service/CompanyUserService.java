package eins.service;

import eins.entity.CompanyUser;

import java.util.List;

public interface CompanyUserService {

    void save(CompanyUser o);

    CompanyUser findOne(int id);

    List<CompanyUser> findAll();

}
