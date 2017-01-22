package model.dao;

import java.util.List;


public interface GenericDao<T> {
	
    T find(int id);

    List<T> findAll();

    void create(T t);

    void update(T t);

    void delete(int id);
}
