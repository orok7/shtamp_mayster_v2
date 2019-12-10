package eins.service;

import eins.entity.Review;

import java.util.List;

public interface ReviewService {

    void save(Review o);

    Review findOne(Long id);

    List<Review> findAll();

    List<Review> findAllByProductId(Long id);

    List<Review> findAllByProductIdWithUsers(Long id);

    List<Review> findAllByUserUsername(String username);

}
