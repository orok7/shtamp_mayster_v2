package eins.service.impl;

import eins.dao.ImageDao;
import eins.entity.Image;
import eins.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao dbDAO;

    @Override
    public void save(Image o) {
        dbDAO.save(o);
    }

    @Override
    public Image findOne(int id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<Image> findAll() {
        return dbDAO.findAll();
    }

    @Override
    public List<Image> findAllByProductId(int id) {
        return dbDAO.findAllByProductId(id);
    }
}