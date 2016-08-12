package com.store.service;

import com.store.model.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character,Long> {
}
