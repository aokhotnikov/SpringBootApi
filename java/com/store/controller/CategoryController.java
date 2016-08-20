package com.store.controller;

import com.store.model.Category;
import com.store.model.Character;
import com.store.model.Tovar;
import com.store.service.CategoryRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.store.service.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Resource(name="tovarRepository")
    private TovarRepository trepository;

    private DataSource dataSource;

    //------------------GET----------------------
    @GetMapping(value = "/category/{cat_id}")
    public Category getCategory(@PathVariable long cat_id){
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category;
        }
        return null;
    }

    //------------------POST----------------------
    @PostMapping(value = "/category")
    public void postCategory(String name, @RequestParam(value="parent_id", defaultValue = "0") long parent_id) {

        Category category = new Category(name, parent_id);
        repository.save(category);
    }

    //------------------PUT----------------------
    @PutMapping(value = "/category/{cat_id}")
    public void putCategory(@PathVariable long cat_id, String name, long parent_id) {

        Category category = repository.findOne(cat_id);
        category.setName(name);
        category.setParent_id(parent_id);
        repository.save(category);
    }

    //------------------DELETE----------------------
    @DeleteMapping(value = "/category/{cat_id}")
    public boolean delCategory(@PathVariable long cat_id) {

        if ((cat_id > 0) && (repository.exists(cat_id))) {
            repository.delete(cat_id);
            System.out.println(String.format("Delete category id = %d", cat_id));
            return true;
        }
        return false;
    }



    //------------------GET ALL CATEGORIES----------------------
    @GetMapping(value = "/categories")
    public Iterable<Category> getCaregories() {
        return repository.findAll();
    }

    //------------------GET TOVARS BY CATEGORY AND VALUES ----------------------
    @GetMapping(value = "/category/{cat_id}/tovars")
    public List<Tovar> getTovars(@PathVariable long cat_id, long[] values) {
        if (cat_id > 0) {

            if (values == null) {
                Category category = repository.findOne(cat_id);
                return category.getTovars();
            }

            return trepository.findByValue(cat_id, values, values.length);
        }
        return null;
    }

    //------------------GET CHARACTERS BY CATEGORY----------------------
    @GetMapping(value = "/category/{cat_id}/characters")
    public List<Character> getCharacters(@PathVariable long cat_id) {
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category.getCharacters();
        }
        return null;
    }
}
