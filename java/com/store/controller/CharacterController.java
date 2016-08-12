package com.store.controller;

import com.store.model.Character;
import com.store.model.Value;
import com.store.service.CharacterRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CharacterController {

    @Resource(name= "characterRepository")
    private CharacterRepository repository;

    //------------------GET----------------------
    @GetMapping(value = "/character/{char_id}")
    public Character getCharacter(@PathVariable long char_id) {

        if (char_id > 0) {
            Character character = repository.findOne(char_id);
            return character;
        }
        return null;
    }

    //------------------POST----------------------
    @PostMapping(value = "/character")
    public void postCharacter(String name) {

        Character character = new Character(name);
        repository.save(character);
    }

    //------------------PUT----------------------
    @PutMapping(value = "/character/{char_id}")
    public void putTCharacter(@PathVariable long char_id, String name) {

        Character character = repository.findOne(char_id);
        character.setName(name);
        repository.save(character);
    }

    //------------------DELETE----------------------
    @DeleteMapping(value = "/character/{char_id}")
    public boolean delCharacter(@PathVariable long char_id) {

        if ((char_id > 0) && (repository.exists(char_id))){
            System.out.println(String.format("Delete character id = %d", char_id));
            repository.delete(char_id);
            return true;
        }
        return false;
    }

    //------------------GET VALUES BY CHARACTER----------------------
//    @GetMapping(value = "/character/{char_id}/values")
//    public List<Value> getValues(@PathVariable long char_id) {
//        if (char_id > 0) {
//            Character character = repository.findOne(char_id);
//            return character.getValues();
//        }
//        return null;
//    }

    //------------------GET ALL CHARACTERS----------------------
    @GetMapping(value = "/characters")
    public Iterable<Character> getCharacters() {
        return repository.findAll();
    }
}
