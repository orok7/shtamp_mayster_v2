package eins.service;

import eins.entity.Review;

import java.util.List;

public interface ReviewService {

    void save(Review o);

    Review findOne(long id);

    List<Review> findAll();

    List<Review> findAllByProductId(long id);

    List<Review> findAllByProductIdWithUsers(long id);

    List<Review> findAllByUserUsername(String username);

}
