package eins.service.impl;

import eins.dao.ReviewDao;
import eins.entity.Review;
import eins.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewDao dbDAO;

    @Override
    public void save(Review o) {
        dbDAO.save(o);
    }

    @Override
    public Review findOne(long id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<Review> findAll() {
        return dbDAO.findAll();
    }

    @Override
    public List<Review> findAllByProductId(long id) {
        return dbDAO.findAllByProductId(id);
    }

    @Override
    public List<Review> findAllByProductIdWithUsers(long id) {
        return dbDAO.findAllByProductIdWithUsers(id);
    }

    @Override
    public List<Review> findAllByUserUsername(String username) {
        return dbDAO.findAllByUserUsername(username);
    }
}