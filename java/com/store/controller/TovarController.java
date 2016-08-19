package com.store.controller;

import com.store.model.Category;
import com.store.model.Tovar;
import com.store.model.Value;
import com.store.service.TovarRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
public class TovarController {

    @Resource(name="tovarRepository")
    private TovarRepository repository;


    //------------------GET----------------------
    @GetMapping(value = "/tovar/{tovarId}")
    public Tovar getTovar(@PathVariable long tovarId) {

        if (tovarId > 0) {
            Tovar tovar = repository.findOne(tovarId);
            //System.out.println(repository.findByName(tovar.getName())); //проба своего метода
            return tovar;
        }
        return null;
    }

    //------------------POST----------------------
    @PostMapping(value = "/tovar")
    public void postTovar(String name, int available,
                          @RequestParam(value="price", defaultValue = "0") double price,
                          @RequestParam(value="garanty",defaultValue = "0") int garanty) {

        Tovar tovar = new Tovar(name,available,price,garanty);
        repository.save(tovar);
    }

    //------------------PUT----------------------
    @PutMapping(value = "/tovar/{tovarId}")
    public void putTovar(@PathVariable long tovarId, String name, int available, double price, int garanty) {

        Tovar tovar = repository.findOne(tovarId);
        tovar.setName(name);
        tovar.setAvailable(available);
        tovar.setPrice(price);
        tovar.setGaranty(garanty);
        repository.save(tovar);
    }

    //------------------DELETE----------------------
    @DeleteMapping(value = "/tovar/{tovarId}")
    public boolean delTovar(@PathVariable long tovarId) {

        if ((tovarId > 0) && (repository.exists(tovarId))){
            System.out.println(String.format("Delete tovar id = %d", tovarId));
            repository.delete(tovarId);
            return true;
        }
        return false;
    }

    //------------------GET TOVAR'S CATEGORIES----------------------
    @GetMapping(value = "/tovar/{tovarId}/categories")
    public List<Category> getCategories(@PathVariable long tovarId) {

        if (tovarId > 0) {
            Tovar tovar = repository.findOne(tovarId);
            return tovar.getCategories();
        }
        return null;
    }

    //------------------GET TOVAR'S VALUES----------------------
    @GetMapping(value = "/tovar/{tovarId}/values")
    public List<Value> getValues(@PathVariable long tovarId) {

        if (tovarId > 0) {
            Tovar tovar = repository.findOne(tovarId);
            return tovar.getValues();
        }
        return null;
    }

    //------------------GET ALL TOVARS----------------------
    @GetMapping(value = "/tovars")
    public Iterable<Tovar> getTovars() {
        return repository.findAll();
    }

    //------------------GET TOVARS BY VALUES----------------------
    @GetMapping(value = "/tovars/value/{valueId}")
    public Set<Tovar> getTovarsByValue(@PathVariable long valueId) {

        return valueId > 0 ? repository.findByValue(valueId) : null;
    }

}

