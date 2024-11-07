package org.example.services;

import org.example.repository.Repository;

public interface Service<T> {
    boolean add();
    boolean print();
    Repository<T> getRepository();
}