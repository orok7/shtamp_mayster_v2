package eins.service;

import java.util.List;

public interface DbService {

    void save(Object o, Class<?> clazz);

//    void save(Map<String, String> map, Class<?> clazz) throws Exception;

    Object findOne(long id, Class<?> clazz);

    List<Object> findAll(Class<?> clazz);

}
