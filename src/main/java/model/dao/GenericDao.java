package model.dao;

import java.util.List;


public interface GenericDao<E> {
	
    E find(int id);

    List<E> findAll();

    void create(E e);

    void update(E e);

    void delete(int id);
}
