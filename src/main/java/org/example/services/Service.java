package org.example.services;

import org.example.repository.Repository;

public interface Service<T> {
    boolean add();
    boolean print();
    boolean edit();
    boolean delete();
    Repository<T> getRepository();
}