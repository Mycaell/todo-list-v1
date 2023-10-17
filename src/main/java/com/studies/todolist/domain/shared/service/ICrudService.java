package com.studies.todolist.domain.shared.service;

import java.util.List;

public interface ICrudService<T, ID> {

    T create(T object);

    T update(ID id, T object);

    List<T> findAll();

    T findById(ID id);

    void delete(ID id);

}
