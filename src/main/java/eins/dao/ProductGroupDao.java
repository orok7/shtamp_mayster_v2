package eins.dao;

import eins.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductGroupDao extends JpaRepository<ProductGroup, Long> {

    @Query("select pg from ProductGroup pg order by pg.name")
    List<ProductGroup> findAllOrderByName();
}
