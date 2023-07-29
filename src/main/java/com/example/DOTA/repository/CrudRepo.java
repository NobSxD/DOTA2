package com.example.DOTA.repository;

public interface CrudRepo {
    void save(Object o);
    void create(Object o);
    void delete(Long id);
}
