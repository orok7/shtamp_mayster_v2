package eins.service;

import eins.entity.Image;

import java.util.List;

public interface ImageService {

    void save(Image o);

    Image findOne(int id);

    List<Image> findAll();

    List<Image> findAllByProductId(int id);

}
