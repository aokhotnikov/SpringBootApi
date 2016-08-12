package com.store.service;

import com.store.model.Tovar;
import org.springframework.data.repository.CrudRepository;


public interface TovarRepository extends CrudRepository<Tovar, Long> {
    Tovar findByName(String name);
}
