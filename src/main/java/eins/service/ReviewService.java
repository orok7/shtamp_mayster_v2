package eins.service;

import eins.entity.Review;

import java.util.List;

public interface ReviewService {

    void save(Review o);

    Review findOne(int id);

    List<Review> findAll();

    List<Review> findAllByProductId(int id);

    List<Review> findAllByProductIdWithUsers(int id);

    List<Review> findAllByUserUsername(String username);

}
