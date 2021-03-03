package repository;

import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T,K> {

    Collection<T> findAll() throws SQLException;

    T findById(K id) throws SQLException;

    void updateById(T object) throws SQLException;

    void insert(T object) throws SQLException;

    void deleteById(K id) throws SQLException;
}
