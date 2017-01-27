package model.dao;

import java.util.List;

/**
 * interface GenericDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface GenericDao<T> {
	
    T find(int id);

    List<T> findAll();

    void create(T t);

    void update(T t);

    void delete(int id);

}
