package com.store.controller;

import com.store.model.Tovar;
import com.store.model.Value;
import com.store.service.ValueRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
public class ValueController {

    @Resource(name= "valueRepository")
    private ValueRepository repository;

    //------------------GET----------------------
    @GetMapping(value = "/value/{val_id}")
    public Value getValue(@PathVariable long val_id) {

        if (val_id > 0) {
            Value value = repository.findOne(val_id);
            return value;
        }
        return null;
    }

    //------------------POST----------------------
    @PostMapping(value = "/value")
    public void postValue(String name, long char_id) {

        Value value = new Value(name, char_id);
        repository.save(value);
    }

    //------------------PUT----------------------
    @PutMapping(value = "/value/{val_id}")
    public void putTValue(@PathVariable long val_id, String name, long char_id) {

        Value value = repository.findOne(val_id);
        value.setName(name);
        value.setChar_id(char_id);
        repository.save(value);
    }

    //------------------DELETE----------------------
    @DeleteMapping(value = "/value/{val_id}")
    public boolean delValue(@PathVariable long val_id) {

        if ((val_id > 0) && (repository.exists(val_id))){
            System.out.println(String.format("Delete value id = %d", val_id));
            repository.delete(val_id);
            return true;
        }
        return false;
    }

    //------------------GET TOVARS BY VALUE----------------------
    @GetMapping(value = "/value/{val_id}/tovars")
    public Set<Tovar> getTovars(@PathVariable long val_id){

        if (val_id > 0) {
            Value value = repository.findOne(val_id);
            //System.out.println(value);
            return value.getTovars();
        }
        return null;
    }

    //------------------GET ALL VALUES----------------------
    @GetMapping(value = "/values")
    public Iterable<Value> getValues() {
        return repository.findAll();
    }
}
