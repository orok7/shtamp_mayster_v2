package eins.dao;

import eins.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDao extends JpaRepository<Review, Long> {

    List<Review> findAllByProductId(Long id);

    @Query("select r from Review r left outer join fetch r.user where r.product.id=:id")
    List<Review> findAllByProductIdWithUsers(@Param("id") Long id);

    List<Review> findAllByUserUsername(String username);

}