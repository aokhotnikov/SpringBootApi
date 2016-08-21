package com.store.controller;

import com.store.model.Owner;
import com.store.model.Tovar;
import com.store.service.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class OwnerController {

    @Autowired
    private OwnerRepository repository;

    //------------------GET----------------------
    @GetMapping(value = "/owner/{owner_id}")
    public Owner getOwner(@PathVariable long owner_id) {

        if (owner_id > 0) {
            return repository.findOne(owner_id);
        }
        return null;
    }

    //------------------GET ALL OWNERS----------------------
    @GetMapping(value = "/owners")
    public Iterable<Owner> getOwners() {
        return repository.findAll();
    }

    //------------------GET TOVARS BY OWNER----------------------
    @GetMapping(value = "/owner/{owner_id}/tovars")
    public Set<Tovar> getTovars(@PathVariable long owner_id) {

        if (owner_id > 0) {
            return repository.findOne(owner_id).getTovars();
        }
        return null;
    }

}
