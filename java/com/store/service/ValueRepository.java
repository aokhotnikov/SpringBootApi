package com.store.service;

import com.store.model.Value;
import org.springframework.data.repository.CrudRepository;

public interface ValueRepository extends CrudRepository<Value, Long> {
}
