package eins.dao;

import eins.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

//    SELECT * FROM product WHERE product.name LIKE '%австрія%' OR description LIKE '%австрія%';
    @Query("select p from Product p where p.name like :searchThis or p.description like :searchThis")
    List<Product> findAllBySearch(@Param("searchThis") String searchThis);

    @Query("select p from Product p left outer join fetch p.reviews order by p.name")
    List<Product> findAllWithGroups();

    @Query("select p from Product p left outer join fetch p.group where p.id=:id")
    Product findOneWithGroup(@Param("id") long id);

    List<Product> findAllByGroupName(String name);

}
