package eins.dao;

import eins.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDao extends JpaRepository<Review, Integer> {

    List<Review> findAllByProductId(int id);

    @Query("select r from Review r left outer join fetch r.user where r.product.id=:id")
    List<Review> findAllByProductIdWithUsers(@Param("id") int id);

    List<Review> findAllByUserUsername(String username);

}