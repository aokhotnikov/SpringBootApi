package com.store.service;

import com.store.model.Tovar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TovarRepository extends CrudRepository<Tovar, Long> {
    Tovar findByName(String name);

    @Query("SELECT t FROM Category c " +
           "LEFT JOIN c.tovars t " +
           "LEFT JOIN t.values v " +
           "WHERE c.id=:cat_id AND v.id IN (:value_id) " +
           "GROUP BY t.name " +
           "HAVING COUNT(*) = :count_value")
    List<Tovar> findByValue(@Param("cat_id") long cat_id, @Param("value_id") long[] value_id, @Param("count_value") long count_value);
}
