package org.example.repository;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    void saveAll(List<T> entities);
    void delete(T entity);
    List<T> load();
}