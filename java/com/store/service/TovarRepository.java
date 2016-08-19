package com.store.service;

import com.store.model.Tovar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface TovarRepository extends CrudRepository<Tovar, Long> {
    Tovar findByName(String name);

    @Query("SELECT t from Tovar t LEFT JOIN t.values v where v.id=:value_id")
    Set<Tovar> findByValue(@Param("value_id") long value_id);
}
